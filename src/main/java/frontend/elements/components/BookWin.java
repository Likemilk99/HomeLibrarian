package frontend.elements.components;

import Data.Books;
import Data.Users;
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
import frontend.views.AddView;
import org.apache.tika.Tika;
import org.vaadin.teemu.ratingstars.RatingStars;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    private Embedded embedded = new Embedded();
    // right is author, name, year, rating
    //  ?tags?, description
    private VerticalLayout fieldsPane = new VerticalLayout();
    private RatingStars rating = new RatingStars();

    private final LineBreakCounter bookLineBreakCounter = new LineBreakCounter();
    private final Upload bookUpload = new Upload(null, bookLineBreakCounter);

    private final ImageUploader imageLineBreakCounter = new ImageUploader();
    private final Upload imageUpload = new Upload(null, imageLineBreakCounter);

    //private UploadInfoWindow imageUploadInfoWindow = new UploadInfoWindow(imageUpload, imageLineBreakCounter);
    private UploadInfoWindow bookUploadInfoWindow = new UploadInfoWindow(bookUpload, bookLineBreakCounter);

    private TextField authorField = new TextField("Author");
    private TextField nameField = new TextField("Name");
    private TextField seriesField = new TextField("Series");
    private NativeSelect yearSelect = new NativeSelect("Year");
    private TextField yearField = new TextField("Year");

    private TextArea descArea = new TextArea("Description");

    private final HorizontalLayout buttons = new HorizontalLayout();
    private final Button addButton = new Button("Add");
    private final Button cancelButton = new Button("Cancel");

    private Books book;

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
        // TODO SQL
        //embedded = new Embedded()
        embedded.setVisible(true);

        filesPane.addComponent(rating);
        filesPane.addComponent(embedded);

        filesPane.setWidth(320, Unit.PIXELS);
        //
        authorField.setReadOnly(true);
        nameField.setReadOnly(true);
        yearField.setReadOnly(true);
        seriesField.setReadOnly(true);
        descArea.setReadOnly(true);

        authorField.setValue(book.getAuthor());
        nameField.setValue(book.getTitle());
        yearField.setValue(book.getYear());
        // TODO SQL
        seriesField.setValue("");
        descArea.setValue("");

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

        //
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

        setStyleName("bookinfolayout");
        // images + files
        bookLineBreakCounter.setSlow(true);
        //imageLineBreakCounter.setSlow(true);

        dropImagePane.setComponentAlignment(imageLabel, Alignment.MIDDLE_CENTER);
        dropImagePane.setWidth(310, Unit.PIXELS);
        dropImagePane.setHeight(460, Unit.PIXELS);
        dropImagePane.addStyleName("drop-area");

        dropBox.setSizeUndefined();

        embedded.setVisible(false);

        bookUpload.setImmediate(false);

        bookUpload.setWidth(310, Unit.PIXELS);

        bookUpload.setButtonCaption(null);

        bookUpload.addChangeListener(new Upload.ChangeListener()
        {
            @Override
            public void filenameChanged(Upload.ChangeEvent event)
            {
                if (event.getFilename() != null) {
                    Tika tika = new Tika();
                    String mimeType = tika.detect(event.getFilename());

                    if (mimeType != null) {// && mimeType.startsWith("image/")) {
                        //imageUpload.setButtonCaption("UPLOAD BOOK");
                    } else {
                        new Notification("File type is not supported  \n" + mimeType,
                                Notification.Type.ERROR_MESSAGE)
                                .show(Page.getCurrent());
                        bookUpload.setButtonCaption(null);
                    }
                }
            }
        });

        bookUpload.addStartedListener(new Upload.StartedListener() {
            @Override
            public void uploadStarted(final Upload.StartedEvent event) {
                if (bookUploadInfoWindow.getParent() == null) {
                    UI.getCurrent().addWindow(bookUploadInfoWindow);
                }
                bookUploadInfoWindow.setClosable(false);
            }
        });
        bookUpload.addFinishedListener(new Upload.FinishedListener() {
            @Override
            public void uploadFinished(final Upload.FinishedEvent event) {
                bookUploadInfoWindow.setClosable(true);
            }
        });

        imageUpload.setImmediate(false);

        imageUpload.setWidth(310, Unit.PIXELS);

        imageUpload.addSucceededListener(imageLineBreakCounter);
        //imageUpload.interruptUpload();

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
                        imageUpload.setButtonCaption("UPLOAD IMAGE");
                    } else {
                        new Notification("File is not image  \n",
                                Notification.Type.ERROR_MESSAGE)
                                .show(Page.getCurrent());
                        imageUpload.setButtonCaption(null);
                    }
                }
            }
        });

        filesPane.addComponent(rating);
        filesPane.addComponent(dropBox);
        filesPane.addComponent(embedded);
        filesPane.addComponent(imageUpload);
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

        authorField.setWidth(310, Unit.PIXELS);
        nameField.setWidth(310, Unit.PIXELS);
        seriesField.setWidth(310, Unit.PIXELS);
        descArea.setWidth(310, Unit.PIXELS);

        //
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
                if (!addButtonClick(nameField.getValue(),
                        yearSelect.getItemCaption(yearSelect.getValue()),
                        authorField.getValue(),
                        dropBox.getFileName()
                )) {

                    //password.setValue("");
                    //reenter.setValue("");
                }
            }
        });
        addButton.setStyleName("super-button");
        addButton.setWidth(310, Unit.PIXELS);

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
        contentLayout.addComponent(dataLayout);
        contentLayout.addComponent(buttons);
        contentLayout.setComponentAlignment(dataLayout, Alignment.MIDDLE_CENTER);
        contentLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
        contentLayout.setSpacing(true);
        contentLayout.setMargin(true);
        //contentLayout.setHeight(100, Unit.PERCENTAGE);
    }

    public Books getBook() {
        return book;
    }

    private boolean addButtonClick(String Title, String Year, String Author, String Res
    ) {
        //TODO closing windows if-else
        //TO TABLE
        book  = new Books(Title, Year, Author, Res);
        this.close();
        // TODO SQL
        return false;
    }

    private static class LineBreakCounter implements Upload.Receiver {
        private int counter;
        private int total;
        private boolean sleep;

        /**
         * return an OutputStream that simply counts lineends
         */
        @Override
        public OutputStream receiveUpload(final String filename,
                                          final String MIMEType) {
            counter = 0;
            total = 0;
            return new OutputStream() {
                private static final int searchedByte = '\n';

                @Override
                public void write(final int b) throws IOException {
                    total++;
                    if (b == searchedByte) {
                        counter++;
                    }
                    if (sleep && total % 1000 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }

        public int getLineBreakCount() {
            return counter;
        }

        public void setSlow(final boolean value) {
            sleep = value;
        }

    }

    @StyleSheet("uploadexample.css")
    private class UploadInfoWindow extends Window implements
            Upload.StartedListener, Upload.ProgressListener,
            Upload.FailedListener, Upload.SucceededListener,
            Upload.FinishedListener {
        private final Label state = new Label();
        private final Label result = new Label();
        private final Label fileName = new Label();
        private final Label textualProgress = new Label();

        private final ProgressBar progressBar = new ProgressBar();
        private final Button cancelButton;
        private final LineBreakCounter counter;

        public UploadInfoWindow(final Upload upload,
                                final LineBreakCounter lineBreakCounter) {
            super("Status");
            this.counter = lineBreakCounter;

            setWidth(700, Unit.PIXELS);
            //setSizeUndefined();

            addStyleName("upload-info");

            setResizable(false);
            setDraggable(false);

            final FormLayout l = new FormLayout();
            setContent(l);
            l.setMargin(true);

            final HorizontalLayout stateLayout = new HorizontalLayout();
            stateLayout.setSpacing(true);
            stateLayout.addComponent(state);

            cancelButton = new Button("Cancel");
            cancelButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    upload.interruptUpload();
                }
            });
            cancelButton.setVisible(false);
            cancelButton.setStyleName("small");
            stateLayout.addComponent(cancelButton);

            stateLayout.setCaption("Current state");
            state.setValue("Idle");
            l.addComponent(stateLayout);

            fileName.setCaption("File name");
            l.addComponent(fileName);

            result.setCaption("Line breaks counted");
            l.addComponent(result);

            progressBar.setCaption("Progress");
            progressBar.setVisible(false);
            l.addComponent(progressBar);

            textualProgress.setVisible(false);
            l.addComponent(textualProgress);

            upload.addStartedListener(this);
            upload.addProgressListener(this);
            upload.addFailedListener(this);
            upload.addSucceededListener(this);
            upload.addFinishedListener(this);
        }

        @Override
        public void uploadFinished(final Upload.FinishedEvent event) {
            state.setValue("Idle");
            progressBar.setVisible(false);
            textualProgress.setVisible(false);
            cancelButton.setVisible(false);

            //embedded.setSizeUndefined();
            embedded.setWidth(310, Unit.PIXELS);
            embedded.setHeight(460, Unit.PIXELS);
            embedded.setVisible(true);
            dropBox.setVisible(false);
            close();
        }

        @Override
        public void uploadStarted(final Upload.StartedEvent event) {
            // this method gets called immediately after upload is started
            progressBar.setValue(0f);
            progressBar.setVisible(true);
            UI.getCurrent().setPollInterval(500);
            textualProgress.setVisible(true);
            // updates to client
            state.setValue("Uploading");
            fileName.setValue(event.getFilename());

            cancelButton.setVisible(true);
        }

        @Override
        public void updateProgress(final long readBytes,
                                   final long contentLength) {
            // this method gets called several times during the update
            progressBar.setValue(new Float(readBytes / (float) contentLength));
            textualProgress.setValue("Processed " + readBytes + " bytes of "
                    + contentLength);
            result.setValue(counter.getLineBreakCount() + " (counting...)");
        }

        @Override
        public void uploadSucceeded(final Upload.SucceededEvent event) {
            result.setValue(counter.getLineBreakCount() + " (total)");
        }

        @Override
        public void uploadFailed(final Upload.FailedEvent event) {
            result.setValue(counter.getLineBreakCount()
                    + " (counting interrupted at "
                    + Math.round(100 * progressBar.getValue()) + "%)");
        }
    }

    @StyleSheet("dragndropexample.css")
    class ImageDropBox extends DragAndDropWrapper implements
            DropHandler {
        private static final long FILE_SIZE_LIMIT = 2 * 1024 * 1024; // 2MB

        private String fileName = new String("");

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
            final StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
                @Override
                public InputStream getStream() {
                    if (bas != null) {
                        final byte[] byteArray = bas.toByteArray();
                        return new ByteArrayInputStream(byteArray);
                    }
                    return null;
                }
            };
            final StreamResource resource = new StreamResource(streamSource,
                    name);

            // show the file contents - images only for now
            //embedded = new Embedded(name, resource);
            embedded.setSource(resource);
            //embedded.setSizeUndefined();
            embedded.setWidth(310, Unit.PIXELS);
            embedded.setHeight(460, Unit.PIXELS);
            embedded.setVisible(true);
            dropBox.setVisible(false);
            //showComponent(embedded, name);
        }

        @Override
        public AcceptCriterion getAcceptCriterion() {
            return AcceptAll.get();
        }
    }

    /**Writes to nowhere*/
    public class NullOutputStream extends OutputStream {
        @Override
        public void write(int b) throws IOException {
        }
    }

    class ImageUploader implements Upload.Receiver, Upload.SucceededListener {
        public File file;

        public OutputStream receiveUpload(String filename,
                                          String mimeType) {
            FileOutputStream fos = null;

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
            embedded.setVisible(true);
            embedded.setSource(new FileResource(file));
            embedded.setWidth(310, Unit.PIXELS);
            embedded.setHeight(460, Unit.PIXELS);
        }
    };
}
