package healthy_application.dao;

import android.database.Cursor;

/**
 * Created by Home on 25/10/2016.
 */

public interface IDao <Entity>{
    public boolean insert(Entity entity);
    public boolean update(Entity entity);
    public Cursor search();
    public int delete(Entity entity);
}
