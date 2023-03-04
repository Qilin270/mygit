package cn.test.mapper;

import cn.test.pojo.Wallet;
import cn.test.pojo.WalletLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

//创建钱包mapper接口
@Mapper
public interface WalletLogMapper extends BaseMapper<WalletLog> {

}

