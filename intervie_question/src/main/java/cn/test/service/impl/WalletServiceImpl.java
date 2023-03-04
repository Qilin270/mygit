package cn.test.service.impl;

import cn.test.mapper.WalletLogMapper;
import cn.test.mapper.WalletMapper;
import cn.test.pojo.Wallet;
import cn.test.pojo.WalletLog;
import cn.test.service.WalletService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {

    @Autowired
    private WalletLogMapper walletLogMapper;

    @Override
    public BigDecimal getBalance(Long userId) {
        //根据用户id查询钱包对象并返回余额属性，如果不存在则抛出异常或返回0元
        //查询用户的钱包信息
        QueryWrapper<Wallet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Wallet wallet = this.getOne(queryWrapper);

        //判断钱包是否存在，如果不存在则抛出异常
        if (wallet == null) {
            throw new RuntimeException("钱包不存在");
        }

        return wallet.getBalance();
    }

    @Override
    public boolean balanceChange(Long userId, BigDecimal balanceChange) {

        //查询用户的钱包信息并获取钱包金额
        BigDecimal balanceOld = getBalance(userId);
        //计算新的余额，并更新钱包表中的数据
        BigDecimal balanceNew = balanceOld.add(balanceChange);

        //判断钱包金额是否足够，如果不够则抛出异常
        BigDecimal zore = new BigDecimal("0.00");
        int result = balanceNew.compareTo(zore); // result is -1, meaning a is
        if (result == -1) {
            throw new RuntimeException("钱包金额不足");
        }

        QueryWrapper<Wallet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Wallet wallet = this.getOne(queryWrapper);

        UpdateWrapper<Wallet> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("balance", balanceNew).eq("id", wallet.getId());
        this.update(updateWrapper);

        //插入一条钱包日志记录到钱包日志表中
        WalletLog walletLog = new WalletLog();
        walletLog.setBalanceOld(balanceOld);
        walletLog.setBalanceNew(balanceNew);
        walletLog.setBalanceChange(balanceChange);
        walletLog.setCreateTime(new Date());
        walletLog.setWalletId(wallet.getId());

        walletLogMapper.insert(walletLog);
        return true;
    }

    @Override
    public List<WalletLog> getWalletLog(Long userId) {

        // 构造查询条件
        QueryWrapper<Wallet> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", userId);
        Wallet wallet = this.getOne(queryWrapper1);

        //判断钱包是否存在，如果不存在则抛出异常
        if (wallet == null) {
            throw new RuntimeException("钱包不存在");
        }

        // 构造查询条件
        QueryWrapper<WalletLog> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("wallet_id", wallet.getId());
        // 调用BaseMapper的selectList方法执行查询并返回结果列表
        List<WalletLog> walletLogs = walletLogMapper.selectList(queryWrapper2);
        if (walletLogs.equals("[]")){
            throw new RuntimeException("近期无金额变动");
        }
        return walletLogs;
    }
}
