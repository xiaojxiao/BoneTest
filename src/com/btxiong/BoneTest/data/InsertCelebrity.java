package com.btxiong.BoneTest.data;

import com.btxiong.BoneTest.util.Constant;

import android.content.Context;

public class InsertCelebrity
{
	private static BoneDAO boneDAO;
	
	public InsertCelebrity(Context context)
	{
		boneDAO = new BoneDAO(context);		
	}
	
	public void Execute()
	{
		boneDAO.deleteAllCelebrity();
		
		boneDAO.insertBone("邓小平", Constant.TYPE_SEX_MALE, 1904, 7, 12, 12, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("比尔·盖茨", Constant.TYPE_SEX_MALE, 1955, 9, 13, 22, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("戴安娜", Constant.TYPE_SEX_FEMALE, 1961, 5, 19, 18, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("普京", Constant.TYPE_SEX_MALE, 1952, 8, 19, 18, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("李连杰", Constant.TYPE_SEX_MALE, 1963, 4, 3, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("李敖", Constant.TYPE_SEX_MALE, 1935, 3, 23, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("李登辉", Constant.TYPE_SEX_MALE, 1922, 11, 29, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("连战", Constant.TYPE_SEX_MALE, 1936, 7, 11, 20, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("李嘉诚", Constant.TYPE_SEX_MALE, 1928, 4, 26, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("里根", Constant.TYPE_SEX_MALE, 1911, 1, 8, 2, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("肯尼迪", Constant.TYPE_SEX_MALE, 1917, 4, 9, 14, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("布什", Constant.TYPE_SEX_MALE, 1924, 5, 11, 12, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("小布什", Constant.TYPE_SEX_MALE, 1946, 6, 8, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("乔丹", Constant.TYPE_SEX_MALE, 1963, 1, 24, 10, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("史泰龙", Constant.TYPE_SEX_MALE, 1946, 6, 8, 20, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("斯瓦辛格", Constant.TYPE_SEX_MALE, 1947, 6, 3, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("梦露", Constant.TYPE_SEX_FEMALE, 1926, 4, 21, 10, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("猫王", Constant.TYPE_SEX_MALE, 1934, 12, 4, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("迈克尔·杰克逊", Constant.TYPE_SEX_MALE, 1958, 7, 15, 20, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("伊丽萨白二世", Constant.TYPE_SEX_FEMALE, 1926, 3, 10, 2, 40, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("陈毅", Constant.TYPE_SEX_MALE, 1901, 7, 13, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("刘德华", Constant.TYPE_SEX_MALE, 1961, 9, 27, 8, 18, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("邓丽君", Constant.TYPE_SEX_FEMALE, 1953, 1, 29, 19, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("朱镕基", Constant.TYPE_SEX_MALE, 1928, 9, 10, 6, 15, Constant.TYPE_RECORD_CELEBRITY);
	}
}
