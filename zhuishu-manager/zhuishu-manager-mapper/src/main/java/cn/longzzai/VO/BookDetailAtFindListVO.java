package cn.longzzai.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 在通过url查询分类下的bookdetail中间件
 *utl:例：http://novel.juhe.im/category-info?type=over&major=%E7%8E%84%E5%B9%BB&minor=&start=1&limit=20
 * @author longcho
 * 2017-09-18-13:40
 */
@Data
public class BookDetailAtFindListVO {
    @JsonProperty("_id")
    private String bookId;
    @JsonProperty("author")
    private String bookAuthor;
    @JsonProperty("cover")
    private String bookCover;
    @JsonProperty("shortIntro")
    private String bookIntro;
    @JsonProperty("title")
    private String bookTitle;
    @JsonProperty("majorCate")
    private String majorCateName;
    @JsonProperty("minorCate")
    private String minorCateName;
    @JsonProperty("latelyFollower")
    private Long latelyFollower;
    @JsonProperty("retentionRatio")
    private BigDecimal retentionRatio;
    @JsonProperty("lastChapter")
    private String lastChapter;

    //无用
    @JsonProperty("tags")
    private Object tags;
    @JsonProperty("site")
    private Object site;
    @JsonProperty("sizetype")
    private Object sizetype;
    @JsonProperty("superscript")
    private Object superscript;
    @JsonProperty("contentType")
    private Object contentType;
    @JsonProperty("allowMonthly")
    private Object allowMonthly;
    @JsonProperty("banned")
    private Object banned;


    public void setBookCover(String bookCover) {
        String s = bookCover.replaceFirst("[\\s\\S]*agent", "http://statics.zhuishushenqi.com/agent");
        this.bookCover = s;
    }


    //无用的全部设为null
    public Object getTags() {
        return null;
    }
    public Object getSite() {
        return null;
    }
    public Object getSizetype() {
        return null;
    }
    public Object getSuperscript() {
        return null;
    }
    public Object getContentType() {
        return null;
    }

    public Object getAllowMonthly() {
        return allowMonthly;
    }

    public Object getBanned() {
        return banned;
    }
}
