package com.zhangli.test.material_design_application.Bean;

public class MoviesBean {
    public String movie_name;
    public String movie_grade;
    public String movie_type;
    public String movie_director;
    public String movie_casts;
    public String movie_date;
    public String movie_poster;

    public MoviesBean(String movie_name, String movie_grade, String movie_type,String movie_director, String movie_casts, String movie_date, String movie_poster) {
        this.movie_name = movie_name;
        this.movie_grade = movie_grade;
        this.movie_type = movie_type;
        this.movie_director = movie_director;
        this.movie_casts = movie_casts;
        this.movie_date = movie_date;
        this.movie_poster = movie_poster;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_grade() {
        return movie_grade;
    }

    public void setMovie_grade(String movie_grade) {
        this.movie_grade = movie_grade;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public String getMovie_casts() {
        return movie_casts;
    }

    public void setMovie_casts(String movie_casts) {
        this.movie_casts = movie_casts;
    }

    public String getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }

    public String getMovie_poster() {
        return movie_poster;
    }

    public void setMovie_poster(String movie_poster) {
        this.movie_poster = movie_poster;
    }

    @Override
    public String toString() {
        return "MoviesBean{" +
                "movie_name='" + movie_name + '\'' +
                ", movie_grade='" + movie_grade + '\'' +
                ", movie_type='" + movie_type + '\'' +
                ", movie_director='" + movie_director + '\'' +
                ", movie_casts='" + movie_casts + '\'' +
                ", movie_date='" + movie_date + '\'' +
                ", movie_poster='" + movie_poster + '\'' +
                '}';
    }
}
