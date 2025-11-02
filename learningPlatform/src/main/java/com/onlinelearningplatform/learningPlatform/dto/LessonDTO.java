package com.onlinelearningplatform.learningPlatform.dto;

public class LessonDTO {
    private Long lessonId;
    private String title;
    private String content;
    private String videoUrl;

    public LessonDTO() {
    }

    public LessonDTO(Long lessonId, String title, String content, String videoUrl) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
