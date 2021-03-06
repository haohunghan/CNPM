package myjava.mybignumber;

    /**
     * Author: Nguyễn Văn Hải
     * Description:
     * IReceiver là interface cho phép in từng bước cộng 2 số
     * Hàm send cho phép in ra chuỗi các bước thực hiện cộng 2 chuỗi số
     * */

public interface IReceiver {

    /**
     * Để thực hiện việc in từng bước cộng 2 chuỗi số 
     *thì phải implements interface này và thực hiện hàm send của IReceiver
     * @since 20/12/2018
     * */

    void send(String msg);
}
