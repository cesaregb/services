package com.il.sod.db.dao.impl;

import org.springframework.stereotype.Service;

@Service
public class SocialNetworkServiceDaoImpl {
//	@Resource
//	private SocialNetworkRepository SocialNetworkRepository;
//
//	@Override
//	@Transactional
//	public SocialNetwork create(SocialNetwork SocialNetwork) {
//		SocialNetwork createdSocialNetwork = SocialNetwork;
//		return SocialNetworkRepository.save(createdSocialNetwork);
//	}
//
//	@Override
//	@Transactional
//	public SocialNetwork findById(int id) {
//		return SocialNetworkRepository.findOne(id);
//	}
//
//	@Override
//	@Transactional(rollbackFor = SODAPIException.class)
//	public SocialNetwork delete(int id) throws SODAPIException {
//		SocialNetwork deletedSocialNetwork = SocialNetworkRepository.findOne(id);
//
//		if (deletedSocialNetwork == null)
//			throw new SODAPIException("");
//
//		SocialNetworkRepository.delete(deletedSocialNetwork);
//		return deletedSocialNetwork;
//	}
//
//	@Override
//	@Transactional
//	public List<SocialNetwork> findAll() {
//		return SocialNetworkRepository.findAll();
//	}
//
//	@Override
//	@Transactional(rollbackFor = SODAPIException.class)
//	public SocialNetwork update(SocialNetwork SocialNetwork) throws SODAPIException {
//		SocialNetwork updatedSocialNetwork = SocialNetworkRepository.findOne(SocialNetwork.getIdSocialNetworks());
//
//		if (updatedSocialNetwork == null)
//			throw new SODAPIException("");
//
//		updatedSocialNetwork.setName(SocialNetwork.getName());
//		return updatedSocialNetwork;
//	}
}