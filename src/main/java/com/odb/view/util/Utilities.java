package com.odb.view.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

import com.odb.core.ViewConfiguration;
import com.odb.view.dashboard.client.dto.DataSourceAxisDetailInfo;
import com.odb.view.dashboard.client.dto.DataSourceAxisInfo;
import com.odb.view.dashboard.client.dto.DataSourceInfo;
import com.odb.view.dashboard.client.dto.GraphInfo;
import com.odb.view.dashboard.client.dto.SubscriberDataSource;
import com.odb.view.dashboard.client.dto.SubscriberInfo;
import com.odb.view.dashboard.client.dto.SubscriberViewConfiguration;
import com.odb.view.dashboard.client.dto.ViewConfig;


/**
 * The Class Utilities.
 */
public class Utilities {

	
	/** The Constant KEY. */
	private static final char[] KEY = "opendashboard".toCharArray();
    
    /** The Constant SALT. */
    private static final byte[] SALT = {
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    /**
     * Encrypt the password.
     *
     * @param password the password
     * @return the encrypted password string
     * @throws GeneralSecurityException the general security exception
     */
    public static String encrypt(String password) throws GeneralSecurityException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(password.getBytes()));
    }

    /**
     * Base64 encode.
     *
     * @param bytes the bytes
     * @return the encoded string
     */
    private static String base64Encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * Decrypt.
     *
     * @param password the password
     * @return the string
     * @throws GeneralSecurityException the general security exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String decrypt(String password) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(password)));
    }

    /**
     * Base64 decode.
     *
     * @param password the password
     * @return the decoded byte[]
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static byte[] base64Decode(String password) throws IOException {
        return Base64.decodeBase64(password.getBytes());
    }
    
    /**
     * Gets the client subscriber info.
     *
     * @param si the si
     * @return the client subscriber info
     */
    public static SubscriberInfo getClientSubscriberInfo(com.odb.core.SubscriberInfo si){
    	SubscriberInfo subscriberInfo=new SubscriberInfo();
    	subscriberInfo.setSubscriberID(si.getSubscriberID());
    	subscriberInfo.setSubscriberName( si.getSubscriberName());
    	subscriberInfo.setSubscriberPassword( si.getSubscriberPassword());
    	return subscriberInfo;
    }
    
    /**
     * Gets the client view config.
     *
     * @param viewConfiguration the view configuration
     * @return the client view config
     */
    public static ViewConfig getClientViewConfig(ViewConfiguration viewConfiguration){
    	ViewConfig viewConfig= new ViewConfig();
    	viewConfig.setDisplayed(viewConfiguration.isDisplayed());
		viewConfig.setStartX(viewConfiguration.getStartX());
		viewConfig.setStartY(viewConfiguration.getStartY());
		viewConfig.setViewHeight(viewConfiguration.getViewHeight());
		viewConfig.setViewWidth(viewConfiguration.getViewWidth());
		viewConfig.setViewLocationID(viewConfiguration.getViewLocationID());
		return viewConfig;
    }
    
    /**
     * Gets the client data source axis detail info.
     *
     * @param dataSourceAxisDetailInfo the data source axis detail info
     * @return the client data source axis detail info
     */
    public static DataSourceAxisDetailInfo getClientDataSourceAxisDetailInfo(com.odb.core.DataSourceAxisDetailInfo dataSourceAxisDetailInfo){
    	DataSourceAxisDetailInfo dsi = new DataSourceAxisDetailInfo();
    	dsi.setAxisLabelIndex(dataSourceAxisDetailInfo.getAxisLabelIndex());
    	dsi.setAxisLabelValue( dataSourceAxisDetailInfo.getAxisLabelValue());
    	dsi.setDataSourceAxisID(dataSourceAxisDetailInfo.getDataSourceAxisID());
		return dsi;
    }
	
	/**
	 * Gets the client data source axis info.
	 *
	 * @param dataSourceAxisInfo the data source axis info
	 * @param dataSourceAxisDetailInfoList the data source axis detail info list
	 * @return the client data source axis info
	 */
	public static DataSourceAxisInfo getClientDataSourceAxisInfo(com.odb.core.DataSourceAxisInfo dataSourceAxisInfo, List<com.odb.core.DataSourceAxisDetailInfo> dataSourceAxisDetailInfoList){
		DataSourceAxisInfo dsi = new DataSourceAxisInfo();
		dsi.setDataSourceAxisID( dataSourceAxisInfo.getDataSourceAxisID());
		dsi.setDataSourceAxisName( dataSourceAxisInfo.getDataSourceAxisName());
		dsi.setDataSourceAxisType( dataSourceAxisInfo.getDataSourceAxisType());
		dsi.setDataSourceID( dataSourceAxisInfo.getDataSourceID());
		ArrayList<DataSourceAxisDetailInfo> clientDataSourceAxisDetailInfoList = new ArrayList<DataSourceAxisDetailInfo>(dataSourceAxisDetailInfoList.size());
		for (com.odb.core.DataSourceAxisDetailInfo dataSourceAxisDetailInfo : dataSourceAxisDetailInfoList) {
			clientDataSourceAxisDetailInfoList.add(Utilities.getClientDataSourceAxisDetailInfo(dataSourceAxisDetailInfo));
		}
		dsi.setDataSourceAxisDetailInfoList(clientDataSourceAxisDetailInfoList);
		return dsi;
	}
	
	/**
	 * Gets the client data source info.
	 *
	 * @param dataSourceInfo the data source info
	 * @return the client data source info
	 */
	public static DataSourceInfo getClientDataSourceInfo(com.odb.core.DataSourceInfo dataSourceInfo){
		DataSourceInfo dsi = new DataSourceInfo();	
		dsi.setDataSourceID(dataSourceInfo.getDataSourceID());
		dsi.setPublisherID(dataSourceInfo.getPublisherID());
		dsi.setDataSourceName(dataSourceInfo.getDataSourceName());
		dsi.setTimeoutInterval(dataSourceInfo.getTimeoutInterval());
		dsi.setSeriesCount(dataSourceInfo.getSeriesCount());
		return dsi;
	}
	
	/**
	 * Gets the client subscriber data source.
	 *
	 * @param subscriberDataSource the subscriber data source
	 * @return the client subscriber data source
	 */
	public static SubscriberDataSource getClientSubscriberDataSource(com.odb.core.SubscriberDataSource subscriberDataSource){
		SubscriberDataSource sds = new SubscriberDataSource();
		sds.setDataSourceID(subscriberDataSource.getDataSourceID());
		sds.setGraphID(subscriberDataSource.getGraphID());
		sds.setSubscriberDataSourceID(subscriberDataSource.getSubscriberDataSourceID());
		sds.setSubscriberID(subscriberDataSource.getSubscriberID());
		sds.setSubscriberViewConfiguration( getCliectSubscriberViewConfiguration(subscriberDataSource.getSubscriberViewConfiguration()));
		sds.setGraphInfo(new GraphInfo(subscriberDataSource.getGraphInfo().getGraphID(), subscriberDataSource.getGraphInfo().getGraphName(), subscriberDataSource.getGraphInfo().getGraphType()));
		return sds;
	}

	/**
	 * Gets the cliect subscriber view configuration.
	 *
	 * @param subscriberViewConfiguration the subscriber view configuration
	 * @return the cliect subscriber view configuration
	 */
	public static SubscriberViewConfiguration getCliectSubscriberViewConfiguration(com.odb.core.SubscriberViewConfiguration subscriberViewConfiguration){
		SubscriberViewConfiguration svc = new SubscriberViewConfiguration();
		svc.setSubscriberID(subscriberViewConfiguration.getSubscriberID());
		svc.setSubsriberDataSourceID(subscriberViewConfiguration.getSubsriberDataSourceID());
		svc.setViewLocationID(subscriberViewConfiguration.getViewLocationID());
		return svc;
	}
}
