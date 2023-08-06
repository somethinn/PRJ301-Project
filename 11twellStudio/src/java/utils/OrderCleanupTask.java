/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author ASUS
 */
import java.util.TimerTask;
import order.OrderDAO;

public class OrderCleanupTask extends TimerTask {
    private int orderId;
    
    public OrderCleanupTask(int orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public void run() {
        // Kiểm tra trạng thái của order và xóa nếu là "pending"
        // Gọi phương thức xóa order ở đây
        if (isOrderPending(orderId)) {
            deleteOrder(orderId);
        }
    }
    
    private boolean isOrderPending(int orderId) {
        // Kiểm tra trạng thái của order ở cơ sở dữ liệu
        // Trả về true nếu order status là "pending", ngược lại trả về false
        // Viết mã kiểm tra trạng thái order ở đây
        OrderDAO orderDAO = new OrderDAO();
        if(orderDAO.getOrderStatusByOrderID(orderId) == 0) {
            return true;
        } else {
            return false;
        }
        
        
    }
    
    private void deleteOrder(int orderId) {
        // Xóa order khỏi cơ sở dữ liệu
        // Viết mã xóa order ở đây
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.deleteOrderDetail(orderId);
                orderDAO.deleteOrder(orderId);

    }
}
