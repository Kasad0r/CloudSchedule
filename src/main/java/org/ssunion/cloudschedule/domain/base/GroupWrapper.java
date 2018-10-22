package org.ssunion.cloudschedule.domain.base;

/**
 * @author kasad0r
 */
public class GroupWrapper {
    private String courseName;
    private Iterable<Group> groupList;

    public Iterable<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(Iterable<Group> groupList) {
        this.groupList = groupList;
    }

    public GroupWrapper(Iterable<Group> groupList) {
        this.groupList = groupList;
    }

    public GroupWrapper() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
