
package show;


import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import movie.MovieDTO;
import room.RoomDTO;
import slot.SlotDTO;



@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowDTO {
    private int showID;
    private Date showDate;
    private SlotDTO slot;
    private MovieDTO movie;
    private RoomDTO room;
    private double showPrice;
    private int showStatus;
}
