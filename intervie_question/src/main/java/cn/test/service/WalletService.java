package cn.test.service;

import cn.test.pojo.Wallet;
import cn.test.pojo.WalletLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

//创建钱包服务接口
@Service
public interface WalletService extends IService<Wallet> {
    //查询用户钱包余额
    BigDecimal getBalance(Long userId);

    //金额变化
    boolean balanceChange(Long userId, BigDecimal amount);

    //查看钱包变化明细
    List<WalletLog> getWalletLog(Long userId);
}
