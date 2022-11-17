package com.gift.entity.contest;

import com.gift.entity.request.Request;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="contest_img")
@Getter
@Setter
public class ContestImg {

    @Id
    @Column(name="contest_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="contest_img_name")
    private String contestImgName;

    @Column(name="contest_ori_img_name")
    private String contestOriImgName;

    @Column(name="contest_img_url")
    private String contestImgUrl;

    @Column(name="contest_rep_img_yn")
    private String contestRepImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contest_id")
    private Contest contest;

    public void updateContestImg(String contestOriImgName, String contestImgName, String contestImgUrl) {
        this.contestOriImgName = contestOriImgName;
        this.contestImgName = contestImgName;
        this.contestImgUrl = contestImgUrl;
    }
}
