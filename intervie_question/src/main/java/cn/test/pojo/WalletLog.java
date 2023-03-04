package cn.test.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//创建钱包实体类
@Data
@TableName("walletlog")
public class WalletLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long walletId;
    private BigDecimal balanceOld;
    private BigDecimal balanceNew;
    private BigDecimal balanceChange;
    private Date createTime;
}