package com.udd.udd.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReviewerWorkKey implements Serializable {

    @Column(name="reviewer_id")
    private Long reviewerId;

    @Column(name="work_id")
    private Long workId;

    public ReviewerWorkKey(){

    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }
}
