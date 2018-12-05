package com.pmcc.revicesell.service;


import com.pmcc.revicesell.dataobject.SellerInfo;
import com.pmcc.revicesell.repository.SellerInfoRepository;
import com.pmcc.revicesell.service.interfaces.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 廖师兄
 * 2017-07-29 23:15
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        return repository.findByOpenid(openid);
    }
}
