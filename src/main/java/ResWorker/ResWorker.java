package ResWorker;

import java.io.File;

/**
 * Created by LikeMilk on 29.03.2016.
 */
public final class ResWorker {
    public static String getImage(String path) {
        File file = new File(path);
        if(file != null) {
            return path;
        } else {
            return "Image/test.jpg";
        }
    }
}
