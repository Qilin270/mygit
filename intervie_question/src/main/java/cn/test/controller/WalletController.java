package cn.test.controller;

import cn.test.pojo.WalletLog;
import cn.test.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    // 1.  查询用户钱包余额接口
    @GetMapping("/balance")
    public BigDecimal getBalance(@RequestParam Long userId) {
        //调用服务层方法获取用户钱包余额并返回，如果发生异常则捕获并处理
        try {
            return walletService.getBalance(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    //2.用户消费100元的接口
    @GetMapping("/consume100")
    public String consume(@RequestParam Long userId) {
        BigDecimal balanceChange = new BigDecimal(-100.00);
        //调用服务层方法执行用户消费操作并返回结果，如果发生异常则捕获并处理
        try {
            if (walletService.balanceChange(userId, balanceChange)) {
                return "消费100元成功!";
            }else{
                return "消费100元失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "消费100元失败";
        }
    }

    //3.用户退还20元的接口
    @GetMapping("/refund20")
    public String refund(@RequestParam Long userId) {
        BigDecimal balanceChange = new BigDecimal(20.00);
        //调用服务层方法执行用户消费操作并返回结果，如果发生异常则捕获并处理
        try {
            if (walletService.balanceChange(userId, balanceChange)) {
                return "退还20元成功!";
            }else{
                return "退还20元失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "退还20元失败";
        }
    }

    //4.查询用户钱包金额变动明细的接口
    @GetMapping("/getWalletLog")
    public List<WalletLog> getWalletLog(@RequestParam Long userId){
        return walletService.getWalletLog(userId);
    }
}