/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.entity;

import java.util.List;

/**
 *
 * @author abdelrazektarek
 */


public class TotalGrade {
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public TotalGrade(List<Grade> grades) {
        this.grades = grades;
    }

    public int getGradeForCriterea(int critereaID)
    {
        for (int i=0; i<grades.size(); i++)
            if (grades.get(i).getCritereaID() == critereaID)
                return grades.get(i).getRatingID();
        
        return -1;
    }
    
    public String getCommentForCriterea(int critereaID)
    {
        for (int i=0; i<grades.size(); i++)
        if (grades.get(i).getCritereaID() == critereaID)
            return grades.get(i).getComment();
        
        return "";
    }
    public float getTotalGrade(){
        float totalGrade = 0;
        
        for (int i=0; i<grades.size(); i++)
            totalGrade += grades.get(i).getGrade();
        return totalGrade;
    }
}
