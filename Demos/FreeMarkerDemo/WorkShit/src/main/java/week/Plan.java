package week;

import java.util.Date;

/**
 * @author beyondlov1
 * @date 2018/11/30
 */
public class Plan {
    private String id;
    private String content;
    private Date start;
    private Date end;
    private Plan prePlan;
    private Plan postPlan;

    public Plan getPrePlan() {
        return prePlan;
    }

    public void setPrePlan(Plan prePlan) {
        this.prePlan = prePlan;
    }

    public Plan getPostPlan() {
        return postPlan;
    }

    public void setPostPlan(Plan postPlan) {
        this.postPlan = postPlan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
