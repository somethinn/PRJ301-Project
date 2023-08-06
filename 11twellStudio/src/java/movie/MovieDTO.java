/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;


import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {
    private int movieID;
    private String genre;
    private String movieTitle;
    private String movieImage;
    private Date moviePremiere;
    private String movieDirector;
    private int movieTime;
    private String movieImageSlide;
    private String movieDescription;
    private String movieTrailerLink;
    private int movieStatus;
    private int movieSliderStatus;
    private int movieNumberOfLike;
    //còn nữa, này test lombok thôi
}
