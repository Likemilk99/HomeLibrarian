package frontend.elements.components;

import DAO.BookDAO;
import DAO.Factory;
import DAO.InterfaceDao;
import DAO.RatingDAO;
import Data.Books;
import Data.Rating;
import Data.Users;
import XMlWorker.Fb2OpenXPATH;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamVariable;
import com.vaadin.ui.*;
import frontend.elements.metro.MetroBook;
import frontend.elements.tablebooks.TableBooks;
import frontend.elements.tableusers.TableUsers;
import frontend.views.AddView;
import org.apache.tika.Tika;
import org.apache.tika.io.FilenameUtils;
import org.vaadin.teemu.ratingstars.RatingStars;

import java.awt.print.Book;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Александр on 30.04.2016.
 */
public class BookWin extends Window {
    private VerticalLayout contentLayout = new VerticalLayout();
    private CssLayout dataLayout = new CssLayout();
    // left is image+upload button
    private VerticalLayout filesPane = new VerticalLayout();

    private final Label imageLabel = new Label("Drop image here");
    private final VerticalLayout dropImagePane = new VerticalLayout(imageLabel);
    private final ProgressBar progress = new ProgressBar();
    private final ImageDropBox dropBox = new ImageDropBox(dropImagePane);

    private RatingStars rating = new RatingStars();
    private Embedded imageEmbedded = new Embedded();

    private VerticalLayout fieldsPane = new VerticalLayout();

    private final bookUploader bookLineBreakCounter = new bookUploader();
    private final Upload bookUpload = new Upload(null, bookLineBreakCounter);
    private File bookFile = new File("");

    private final imageUploader imageLineBreakCounter = new imageUploader();
    private final Upload imageUpload = new Upload(null, imageLineBreakCounter);
    private File imageFile = new File("./resource/default/Logo.png");

    //private UploadInfoWindow imageUploadInfoWindow = new UploadInfoWindow(imageUpload, imageLineBreakCounter);
    //private UploadInfoWindow bookUploadInfoWindow = new UploadInfoWindow(bookUpload, bookLineBreakCounter);

    private TextField nameField = new TextField("Title");
    private TextField seriesField = new TextField("Series");
    private NativeSelect yearSelect = new NativeSelect("Year");
    private TextField yearField = new TextField("Year");
    private TextField authorField = new TextField("Author");

    private TextArea descArea = new TextArea("Description");

    private final HorizontalLayout buttons = new HorizontalLayout();
    private final Button addButton = new Button("Add");
    private final Button cancelButton = new Button("Cancel");

    private Books book;

    private void purgeDirectory(File dir) {
        for (File file: dir.listFiles()) {
            if (file.isDirectory()) purgeDirectory(file);
            if (!file.getName().equals("temp"))
                file.delete();
        }
    }

    public BookWin(Books book, boolean Master) {

    }


    public BookWin(Books book) {
        setModal(true);
        setCaption("Book description");
        setContent(contentLayout);
        setResizable(false);
        center();
        setDraggable(false);

        setStyleName("bookinfolayout");

        // get rating from rating element?

        imageEmbedded.setSource(new FileResource(new File(book.getImage())));
        imageEmbedded.setWidth(310, Unit.PIXELS);
        imageEmbedded.setHeight(460, Unit.PIXELS);
        imageEmbedded.setVisible(true);

        filesPane.addComponent(imageEmbedded);

        filesPane.setWidth(320, Unit.PIXELS);

        authorField.setValue(book.getAuthor());
        nameField.setValue(book.getTitle());
        yearField.setValue(book.getYear());

        seriesField.setValue(book.getSeries());
        descArea.setValue(book.getDescription());

        authorField.setReadOnly(true);
        nameField.setReadOnly(true);
        yearField.setReadOnly(true);
        seriesField.setReadOnly(true);
        descArea.setReadOnly(true);

        authorField.setWidth(310, Unit.PIXELS);
        nameField.setWidth(310, Unit.PIXELS);
        yearField.setWidth(310, Unit.PIXELS);
        seriesField.setWidth(310, Unit.PIXELS);
        descArea.setWidth(310, Unit.PIXELS);

        //
        fieldsPane.addComponent(authorField);
        fieldsPane.addComponent(nameField);
        fieldsPane.addComponent(seriesField);
        fieldsPane.addComponent(yearField);
        fieldsPane.addComponent(descArea);
        fieldsPane.setWidth(320, Unit.PIXELS);
        fieldsPane.setHeight(100, Unit.PERCENTAGE);

        //
        dataLayout.addComponent(filesPane);
        dataLayout.addComponent(fieldsPane);

        cancelButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });

        cancelButton.setWidth(310, Unit.PIXELS);

        buttons.addComponent(cancelButton);
        buttons.setSpacing(true);

        Factory F = new Factory();
        RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);
        try {
            double rat = in.getRaiting(book.getId());

            rating.setValue(rat);
            rating.setReadOnly(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        contentLayout.addComponent(rating);
        contentLayout.addComponent(dataLayout);
        contentLayout.addComponent(buttons);
        contentLayout.setComponentAlignment(dataLayout, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
        contentLayout.setSpacing(true);
        contentLayout.setMargin(true);
        //contentLayout.setHeight(100, Unit.PERCENTAGE);
    }

    public BookWin() {
        setModal(true);
        setCaption("Add book");
        setContent(contentLayout);
        setResizable(false);
        center();
        setDraggable(false);

        this.addCloseListener( e -> {
            purgeDirectory(new File("./tmp/uploads/"));
        });

        setStyleName("bookinfolayout");

        dropImagePane.setComponentAlignment(imageLabel, Alignment.MIDDLE_CENTER);
        dropImagePane.setWidth(310, Unit.PIXELS);
        dropImagePane.setHeight(460, Unit.PIXELS);
        dropImagePane.addStyleName("drop-area");

        dropBox.setSizeUndefined();

        imageEmbedded.setVisible(false);

        bookUpload.setImmediate(false);
        bookUpload.setWidth(310, Unit.PIXELS);
        bookUpload.addSucceededListener(bookLineBreakCounter);
        bookUpload.setButtonCaption(null);

        bookUpload.addChangeListener(new Upload.ChangeListener()
        {
            @Override
            public void filenameChanged(Upload.ChangeEvent event)
            {
                if (event.getFilename() != null) {
                    Tika tika = new Tika();
                    String mimeType = tika.detect(event.getFilename());

                    if (mimeType != null && mimeType.startsWith("application/x-fictionbook+xml")) {
                        bookFile = new File("./tmp/uploads/" + event.getFilename());
                        bookUpload.submitUpload();
                    } else {
                        new Notification("File type is not supported  \n" + mimeType,
                                Notification.Type.ERROR_MESSAGE)
                                .show(Page.getCurrent());
                    }
                }
            }
        });

        imageUpload.setImmediate(false);
        imageUpload.setWidth(310, Unit.PIXELS);
        imageUpload.addSucceededListener(imageLineBreakCounter);
        imageUpload.setButtonCaption(null);

        imageUpload.addChangeListener(new Upload.ChangeListener()
        {
            @Override
            public void filenameChanged(Upload.ChangeEvent event)
            {
                if (event.getFilename() != null) {
                    Tika tika = new Tika();
                    String mimeType = tika.detect(event.getFilename());
                    if (mimeType.startsWith("image/")) {
                        imageFile = new File("./tmp/uploads/" + event.getFilename());
                        imageUpload.submitUpload();

                    } else {
                        new Notification("File is not image  \n",
                                Notification.Type.ERROR_MESSAGE)
                                .show(Page.getCurrent());
                    }
                }
            }
        });

        filesPane.addComponent(new Label("Choose image to upload"));
        filesPane.addComponent(imageUpload);
        filesPane.addComponent(dropBox);
        filesPane.addComponent(imageEmbedded);
        filesPane.setComponentAlignment(imageUpload, Alignment.MIDDLE_CENTER);

        filesPane.setWidth(320, Unit.PIXELS);
        //filesPane.setHeight(100, Unit.PERCENTAGE);
        //
        for (int i=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i>1900; --i) {
            yearSelect.addItem(i);
            yearSelect.setItemCaption(i, Integer.toString(i));
        }
        yearSelect.setValue(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.setWidth(310, Unit.PIXELS);

        authorField.addValueChangeListener(e -> {
            CheckEmptyness();
        });
        nameField.addValueChangeListener(e -> {
            CheckEmptyness();
        });

        authorField.setWidth(310, Unit.PIXELS);
        nameField.setWidth(310, Unit.PIXELS);
        seriesField.setWidth(310, Unit.PIXELS);
        descArea.setWidth(310, Unit.PIXELS);

        //
        fieldsPane.addComponent(new Label("Choose book to upload"));
        fieldsPane.addComponent(bookUpload);
        fieldsPane.addComponent(authorField);
        fieldsPane.addComponent(nameField);
        fieldsPane.addComponent(seriesField);
        fieldsPane.addComponent(yearSelect);
        fieldsPane.addComponent(descArea);
        fieldsPane.setWidth(320, Unit.PIXELS);
        fieldsPane.setHeight(100, Unit.PERCENTAGE);

        fieldsPane.setComponentAlignment(bookUpload, Alignment.MIDDLE_CENTER);
        //
        dataLayout.addComponent(filesPane);
        dataLayout.addComponent(fieldsPane);
        //
        addButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                if (!addButtonClick(nameField.getValue(), seriesField.getValue(),
                        yearSelect.getItemCaption(yearSelect.getValue()),
                        authorField.getValue(), descArea.getValue(),
                        imageFile.getPath(), bookFile.getPath()
                )) {
                }
            }
        });
        addButton.setStyleName("super-button");
        addButton.setWidth(310, Unit.PIXELS);
        addButton.setEnabled(false);

        cancelButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });

        cancelButton.setWidth(310, Unit.PIXELS);

        buttons.addComponent(addButton);
        buttons.addComponent(cancelButton);
        buttons.setSpacing(true);

        //
        contentLayout.addComponent(rating);
        contentLayout.addComponent(dataLayout);
        contentLayout.addComponent(buttons);
        contentLayout.setComponentAlignment(dataLayout, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
        contentLayout.setSpacing(true);
        contentLayout.setMargin(true);
        //contentLayout.setHeight(100, Unit.PERCENTAGE);
    }

    private void CheckEmptyness() {
        addButton.setEnabled(!(authorField.isEmpty() || nameField.isEmpty()));
    }

    public Books getBook() {
        return book;
    }

    private boolean addButtonClick(String Title,
                                   String Series,
                                   String Year,
                                   String Author,
                                   String Description,
                                   String Image,
                                   String File) {
        Factory F = new Factory();
        InterfaceDao InUser = F.getDAO(BookDAO.class);

        try {
            if (InUser.GetByTitleAndName(Title, Author).size() > 0){
                new Notification("Book with given author and name is already exist",
                       Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
                return false;
            }
            book = new Books(Title, Series, Year, Author, Description, Image, File);

            //copy image to
            //copy file to
            String imageextension = "";

            int i = imageFile.getPath().lastIndexOf('.');
            if (i > 0) {
                imageextension = imageFile.getPath().substring(i+1);
            }

            String bookextension = "";

            int j = bookFile.getPath().lastIndexOf('.');
            if (j > 0) {
                bookextension = bookFile.getPath().substring(j+1);
            }

            InUser.addEl(book);
            Books tempbook = (Books)(InUser.GetByTitleAndName(Title, Author)).get(0);
            tempbook.getId();
            String id = tempbook.getId().toString();

            if(!imageFile.equals(new File("./resource/default/Logo.png"))) {
                imageFile.renameTo(new File("./resource/images/" + id + "." + imageextension));
                tempbook.setImage("./resource/images/" + id + "." + imageextension);
            }
            if(!bookFile.equals(new File(""))) {
                bookFile.renameTo(new File("./resource/books/" + id + "." + bookextension));
                tempbook.setFile("./resource/books/" + id + "." + bookextension);
            }

            InUser.updateEl(tempbook);

            try {
                RatingDAO in = (RatingDAO) F.getDAO(RatingDAO.class);

                Rating rat = in.getUser(getSession().getAttribute("user").toString(),book.getId());

                rat.setRaiting(rating.getValue());

                in.updateEl(rat);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            TableBooks.getInstance().updateTable();
            MetroBook.getInstance().updateTable(getSession().getAttribute("user").toString());

            this.close();


        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    class ImageDropBox extends DragAndDropWrapper implements
            DropHandler {
        private static final long FILE_SIZE_LIMIT = 2 * 1024 * 1024; // 2MB

        private String fileName = new String("");
        private File fileFile;
        public String getFileName() {
            return fileName;
        }

        public ImageDropBox(final VerticalLayout root) {
            super(root);
            progress.setIndeterminate(true);
            progress.setVisible(false);
            root.addComponent(progress);

            setDropHandler(this);
        }

        @Override
        public void drop(final DragAndDropEvent dropEvent) {

            // expecting this to be an html5 drag
            final WrapperTransferable tr = (WrapperTransferable) dropEvent
                    .getTransferable();
            final Html5File[] files = tr.getFiles();
            if (files != null) for (final Html5File html5File : files) {
                fileName = html5File.getFileName();

                //fileFile = new File(html5File.getFileName());
                if (html5File.getFileSize() > FILE_SIZE_LIMIT) {
                    Notification
                            .show("File rejected. Max 2Mb files are accepted",
                                    Notification.Type.WARNING_MESSAGE);
                } else {

                    final ByteArrayOutputStream bas = new ByteArrayOutputStream();
                    final StreamVariable streamVariable = new StreamVariable() {

                        @Override
                        public OutputStream getOutputStream() {
                            return bas;
                        }

                        @Override
                        public boolean listenProgress() {
                            return false;
                        }

                        @Override
                        public void onProgress(
                                final StreamingProgressEvent event) {
                        }

                        @Override
                        public void streamingStarted(
                                final StreamingStartEvent event) {
                        }

                        @Override
                        public void streamingFinished(
                                final StreamingEndEvent event) {
                            progress.setVisible(false);
                            showFile(fileName, html5File.getType(), bas);
                        }

                        @Override
                        public void streamingFailed(
                                final StreamingErrorEvent event) {
                            progress.setVisible(false);
                        }

                        @Override
                        public boolean isInterrupted() {
                            return false;
                        }
                    };
                    html5File.setStreamVariable(streamVariable);
                    progress.setVisible(true);
                }
            }
            else {
            }
        }

        private void showFile(final String name, final String type,
                              final ByteArrayOutputStream bas) {
            // resource for serving the file contents

            new Notification("File\n",
             "trololol",
                    Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());

            final StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
                @Override
                public InputStream getStream() {
                    if (bas != null) {
                        final byte[] byteArray = bas.toByteArray();

                        return new ByteArrayInputStream(byteArray) {
                            public void close() throws IOException {
                                super.close();
                            }
                        };
                    }
                    return null;
                }
            };

            int read = 0;
            byte[] bytes = bas.toByteArray();
            OutputStream outputStream = null;
            try {
                fileFile = new File("./tmp/uploads/" + name);
                outputStream = new FileOutputStream( fileFile);

                bas.writeTo(outputStream);
            }
            catch (IOException e) {

            } finally {
                if (outputStream != null) {
                    try {
                        // outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            imageFile = fileFile;
            // show the file contents - images only for now
            //embedded = new Embedded(name, resource);
            imageEmbedded.setSource(new FileResource(imageFile));
            //embedded.setSizeUndefined();
            imageEmbedded.setWidth(310, Unit.PIXELS);
            imageEmbedded.setHeight(460, Unit.PIXELS);
            imageEmbedded.setVisible(true);
            dropBox.setVisible(false);
            //showComponent(embedded, name);
        }

        @Override
        public AcceptCriterion getAcceptCriterion() {
            return AcceptAll.get();
        }
    }

    class imageUploader implements Upload.Receiver, Upload.SucceededListener {
        public File file;

        public OutputStream receiveUpload(String filename,
                                          String mimeType) {
            FileOutputStream fos = null;
           //тут после вставки

            try {
                file = new File("tmp/uploads/" + filename);
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException ex) {
                new Notification("File not found \n",
                        ex.getLocalizedMessage(),
                        Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
            }

            return fos;
        }

        public void uploadSucceeded(Upload.SucceededEvent event) {
            // Show the uploaded file in the image viewer
            new Notification("File loaded",
                    Notification.Type.TRAY_NOTIFICATION)
                    .show(Page.getCurrent());
            dropBox.setVisible(false);
            imageEmbedded.setVisible(true);
            imageEmbedded.setSource(new FileResource(file));
            imageEmbedded.setWidth(310, Unit.PIXELS);
            imageEmbedded.setHeight(460, Unit.PIXELS);
            imageFile = file;
        }
    };

    class bookUploader implements Upload.Receiver, Upload.SucceededListener {
        public File file;
        private String path = "tmp/uploads/";
        private Fb2OpenXPATH worker;
        public OutputStream receiveUpload(String filename,
                                          String mimeType) {
            FileOutputStream fos = null;

            try {
                file = new File(path + filename);

                fos = new FileOutputStream(file);
            } catch (FileNotFoundException ex) {
                new Notification("File not found \n",
                        ex.getLocalizedMessage(),
                        Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
            }

            return fos;
        }

        public void uploadSucceeded(Upload.SucceededEvent event) {
            // Show the uploaded file in the image viewer
            new Notification("File loaded",
                    Notification.Type.TRAY_NOTIFICATION)
                    .show(Page.getCurrent());
            bookFile = file;

            Fb2OpenXPATH Fbxpath = new Fb2OpenXPATH(file.getAbsolutePath());

            authorField.setValue(Fbxpath.GetFName() + " " + Fbxpath.GetMName() + " " + Fbxpath.GetLName());
            nameField.setValue(Fbxpath.GetTitle());

            yearSelect.setValue(Integer.parseInt(Fbxpath.GetDate()));

            seriesField.setValue(Fbxpath.GetBookSeries());

            descArea.setValue(Fbxpath.GetAnnotatiome());
    }
    };
}
