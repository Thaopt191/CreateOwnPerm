package fortestperpose;

import java.security.BasicPermission;

public class HighScorePermission extends BasicPermission {
    public HighScorePermission(String gameName) {
        super(gameName);
    }
    public HighScorePermission(String gameName,String actions){
        super(gameName,actions);
    }
}
