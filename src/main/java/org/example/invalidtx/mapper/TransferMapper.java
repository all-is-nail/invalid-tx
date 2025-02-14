package org.example.invalidtx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.invalidtx.entity.Transfer;

@Mapper
public interface TransferMapper {
    void insertTransfer(Transfer transfer);

    Transfer selectTransferById(long id);
}
