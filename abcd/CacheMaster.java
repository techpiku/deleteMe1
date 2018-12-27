package com.itc.apollo;

import java.io.File;

import net.sf.ehcache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to load the cache configuration outside of the web application and
 * try to maintain only one instance at any point of time.
 * 
 * @author Debadatta Mishra
 */
public class CacheMaster {

	/** The logger. */
	protected Logger logger = LoggerFactory.getLogger(CacheMaster.class);

	/**
	 * private constructor
	 */
	private CacheMaster() {

	}

	/**
	 * Initialize the cache manager from the configuration file placed outside
	 * of the application. If the config file or env setting is not available,it
	 * will load the default configuration.
	 * 
	 * @return the cache manager
	 */
	private CacheManager initCacheManager() {
		CacheManager cacheManager = null;
		String ehcacheConfigPath = System.getenv("EHCACHE_CONFIG");
		if (null == ehcacheConfigPath) {
			System.out.println("Environment variable EHCACHE_CONFIG has not yet set properly.");
			logger.debug("1. Environment variable EHCACHE_CONFIG has not yet set properly.");
			logger.info("Trying to load the default Cache Manager setting ...");
			System.out.println("Trying to load the default Cache Manager setting ...");
			cacheManager = new CacheManager();
		} else {
			File file = new File(ehcacheConfigPath);
			if (!file.exists()) {
				logger.debug("Ehcache config file is not located in the defined path");
				System.out.println("Ehcache config file is not located in the defined path");
				logger.info("Trying to load the default Cache Manager setting ...");
				System.out.println("Trying to load the default Cache Manager setting ...");
				cacheManager = new CacheManager();
			} else {
				cacheManager = CacheManager.create(ehcacheConfigPath);
			}
		}
		return cacheManager;
	}
	

	/**
	 * Internal delegation class to get Cache Manager
	 */
	private static class CacheHolder {

		/** The Constant cacheManager. */
		public static final CacheManager cacheManager = new CacheMaster()
				.initCacheManager();
	}

	/**
	 * Actual method to get the cache manager.
	 * 
	 * @return the cache manager
	 */
	public static CacheManager getCacheManager() {
		return CacheHolder.cacheManager;
	}

}
