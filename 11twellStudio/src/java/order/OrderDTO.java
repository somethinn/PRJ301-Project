/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import user.UserDTO;
import seat.SeatDTO;
import show.ShowDTO;

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
public class OrderDTO {
    private int orderID;
    private UserDTO user;
    private ShowDTO show;
    private List<SeatDTO> seatIDList;
    private int orderDetailQuantity;
    private double orderTotalPrice;
    private int orderStatus;
    private String orderCreatedAt;
    //it should be Date data type .-., but u know..
}
