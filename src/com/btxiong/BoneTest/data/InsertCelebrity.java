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
		
		boneDAO.insertBone("D邓小平", Constant.TYPE_SEX_MALE, 1904, 7, 12, 12, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("B比尔·盖茨", Constant.TYPE_SEX_MALE, 1955, 9, 14, 22, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("D戴安娜", Constant.TYPE_SEX_FEMALE, 1961, 5, 19, 18, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("P普京", Constant.TYPE_SEX_MALE, 1952, 8, 19, 18, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L李连杰", Constant.TYPE_SEX_MALE, 1963, 4, 3, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L李敖", Constant.TYPE_SEX_MALE, 1935, 3, 23, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L李登辉", Constant.TYPE_SEX_MALE, 1922, 11, 29, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L连战", Constant.TYPE_SEX_MALE, 1936, 7, 11, 20, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L李嘉诚", Constant.TYPE_SEX_MALE, 1928, 4, 26, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L里根", Constant.TYPE_SEX_MALE, 1911, 1, 8, 2, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("K肯尼迪", Constant.TYPE_SEX_MALE, 1917, 4, 9, 14, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("B布什", Constant.TYPE_SEX_MALE, 1924, 5, 11, 12, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("X小布什", Constant.TYPE_SEX_MALE, 1946, 6, 8, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("Q乔丹", Constant.TYPE_SEX_MALE, 1963, 1, 24, 10, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("S史泰龙", Constant.TYPE_SEX_MALE, 1946, 6, 8, 20, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("S斯瓦辛格", Constant.TYPE_SEX_MALE, 1947, 6, 3, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("M梦露", Constant.TYPE_SEX_FEMALE, 1926, 4, 21, 10, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("M猫王", Constant.TYPE_SEX_MALE, 1934, 12, 4, 4, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("M迈克尔·杰克逊", Constant.TYPE_SEX_MALE, 1958, 7, 15, 20, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("Y伊丽萨白二世", Constant.TYPE_SEX_FEMALE, 1926, 3, 10, 2, 40, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("C陈毅", Constant.TYPE_SEX_MALE, 1901, 7, 13, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L刘德华", Constant.TYPE_SEX_MALE, 1961, 9, 27, 8, 18, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("D邓丽君", Constant.TYPE_SEX_FEMALE, 1953, 1, 29, 19, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("Z朱镕基", Constant.TYPE_SEX_MALE, 1928, 9, 10, 6, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("L林青霞", Constant.TYPE_SEX_FEMALE, 1954, 10, 8, 17, 37, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("Q乔布斯", Constant.TYPE_SEX_MALE, 1955, 2, 3, 20, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("Z章子怡", Constant.TYPE_SEX_FEMALE, 1979, 1, 13, 15, 15, Constant.TYPE_RECORD_CELEBRITY);
		boneDAO.insertBone("Z张艺谋", Constant.TYPE_SEX_MALE, 1950, 10, 5, 8, 15, Constant.TYPE_RECORD_CELEBRITY);
	}
}
