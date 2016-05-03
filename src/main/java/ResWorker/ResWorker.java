package ResWorker;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;

import java.io.File;

/**
 * Created by LikeMilk on 29.03.2016.
 */
public final class ResWorker {

    public void download() {
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();
        //private Button saveExcel = new Button();
        Resource res = new FileResource(new File(basepath +
                "/WEB-INF/docs/settings.xlsx"));
        FileDownloader fd = new FileDownloader(res);
     //   fd.extend(saveExcel);
    }
}
