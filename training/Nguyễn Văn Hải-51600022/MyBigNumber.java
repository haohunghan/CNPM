package exercise.mybignumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:  Nguyen Van Hai.
 * DesCription.
 * Class MyBigNumber là lớp để Cộng 2 số lớn bằng 2 chuỗi.
 * Hàm sum trong Class MyBigNumber là hàm để thực hiện phép cộng 2 chuỗi số
 */

public class MyBigNumber {

    private IReceiver receivers;

    public MyBigNumber(final IReceiver receivers) {
        this.receivers = receivers;
    }

    /**
     * Để thực hiện phép cộng, ta cần 2 chuỗi làm tham số cho hàm sum trong đó:
     * 2 chuỗi này đều chỉ chứa các kí số từ '0' đến '9'.
     * <br/>
     *
     * @param s1 chuỗi số thứ nhất.
     * @param s2 chuỗi số thứ hai.
     * @return chuỗi có giá trị là tổng của hai số s1 và s2.
     */
    public String sum(final String s1, final String s2) {
        // Buoc 1: lay do dai 2 chuoi
        // Phan khai bao

        String result = "";
        String msg = "";// Chuoi msg se lam tham so cho ham send cua interface IReceiver
        int length1 = s1.length();// do dai chuoi thu 1
        int length2 = s2.length();// do dai chuoi thu 2
        final int max = (length1 > length2) ? length1 : length2;// lay do dau lon nhat giua a va b
        int remember = 0;// Khởi tạo số nhớ = 0 để khi cộng sẽ có vài trường hợp lớn hơn 9
        int local1 = 0;// Vị trí chuỗi s1
        int local2 = 0;// Vị trí chuỗi s2
        char c1;// kí tự c1 dùng để lấy kí tự cuối cùng của chuỗi s1
        char c2;// kí tự c2 dùng để lấy kí tự cuối cùng của chuỗi s2
        int tong = 0;// Khởi tạo biến tổng = 0 để cộng 2 kí tự cuối cùng lại với nhau

        // Kiểm tra từng kí tự của 2 chuỗi s1 và s2 có chữ không 
        for (int i = 0; i < length1 || i < length2; i++) {

            if (Character.isLetter(s1.charAt(i))) {
                // Nếu chuỗi s1 có chữ hoặc kí tự thì sẽ có NumberFormatException
                throw new NumberFormatException("Vị trí " + (i + 1) + " trong chuỗi " + s1
                        + " không phải là số");
            }
        }

        for (int i = 0; i < length2; i++) {

            if (Character.isLetter(s2.charAt(i))) {
                // Nếu chuỗi s2 có chữ hoặc kí tự thì sẽ có NumberFormatException
                throw new NumberFormatException("Vị trí " + (i + 1) + " trong chuỗi " + s2
                        + " không phải là số");
            }
        }

        // Nếu hàm matcher.find() là đúng tức là trong chuỗi s1 có kí tự đặc biệt
        if (matcher1.find()) {
            throw new NumberFormatException("Vị trí " + (matcher1.start() + 1) + " trong chuỗi " + s1
                    + " không phải là số");
        }

        if (matcher2.find()) {
            throw new NumberFormatException("Vị trí " + (matcher2.start() + 1) + " trong chuỗi " + s2
                    + " không phải là số");
        }
        
        // Kiểm tra số âm
        if (s1.charAt(0) == '-') {
            throw new NumberFormatException("Chưa hỗ trợ số âm s1: " + s1);
        } 
        
        if (s2.charAt(0) == '-') {
            throw new NumberFormatException("Chưa hỗ trợ số âm s2: " + s2);
        }

        // Lặp từ 0 đến max lần
        for (int i = 0; i < max; i++) {
            local1 = length1 - i - 1;// cập nhật lại vị trí chuỗi s1
            local2 = length2 - i - 1;// cập nhật lại vị trí chuỗi s2

            // Xét vị trí của 2 chuỗi xem có >= 0 hay không
            c1 = (local1 >= 0) ? s1.charAt(length1 - i - 1) : '0';
            c2 = (local2 >= 0) ? s2.charAt(length2 - i - 1) : '0';

            tong = (c1 - '0') + (c2 - '0') + remember;// chuyển kí tự thành số xong cộng cho số nhớ
            result = (tong % 10) + result;// Lấy kết quả tổng ở trên chia lấy dư cho 10 và ghép vào chuỗi kết quả
            remember = tong / 10;// Cập nhật lại số nhớ

            msg = "Step " + (i + 1) + ": " + c1 + " + " + c2 + " = "
                    + (tong - remember) + " + " + remember + " = " + tong + " . Write " + (tong % 10) + " remember " + remember;
            this.receivers.send(msg);
        }

        if (remember > 0) {
            result = remember + result;// Nếu số nhớ còn dư thì ghép vào chuỗi kết quả
        }

        return result;// Trả về kết quả thu được
    }
}