
package ims.repository;

import ims.entity.Grading;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Moaz
 */
@Singleton
public class GradingRepository {

    private int ID = 1;
    List<Grading> gradings = new ArrayList<Grading>();
    Grading grade;

    // get the grades if it was empty iy will return empty list
    public List<Grading> getGradings() {
      

        return gradings;
    }

    // add grading object to the list
    public void addGrade(Grading grades){
        grades.setId(ID++);
        gradings.add(grades);
    }


    
 
   
}
