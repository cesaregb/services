package com.il.sod.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Constants {
  // TODO clean options not used.
  private Constants() {
  }

  public static final String DATE_FORMAT_JSON = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  // Auth profiles.
  public static final String BASIC_AUTH = "BASIC_AUTH";

  // helper to get values from properties
  public static Config envConfig = ConfigFactory.load().getConfig(Constants.COM_IL_SOD_APPLICATION);

  // profiles
  public static final String SPRING_PROFILE_LOCAL = "local";
  public static final String SPRING_PROFILE_DOCKER = "docker";
  public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
  public static final String SPRING_PROFILE_PRODUCTION = "prod";


  // properties.
  public static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

  // env vars if exist (Override existing info)
  public static final String PROPERTY_NAME_DB_URL = "DB_URL";
  public static final String PROPERTY_NAME_DB_USER = "DB_USER";
  public static final String PROPERTY_NAME_DB_PASSWORD = "DB_PASSWORD";

  public static final String DOCKER_ENV_FLAG = "DB_URL";


  public static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.il.sod.db.model.entities";

  public static final String ENV_APP_PROFILE = "APP_PROFILE";


  // for spects, when we are assigning type
  public static final int SPEC_TYPE_PRODUCT = 2;
  public static final int SPEC_TYPE_VALUES = 1;


  // order status
  public static final int ORDER_CREATED = 0;
  public static final int ORDER_PICKED_UP = 1;
  public static final int ORDER_STARTED = 2;
  public static final int ORDER_FINISHED = 3;
  public static final int ORDER_DELIVERED = 4;

  // ClientPaymentInfo type values.
  public static final int CLIENT_PAYMENT_CASH = 0;
  public static final int CLIENT_PAYMENT_STRIPE = 3;
  public static final int CLIENT_PAYMENT_CC = 1; // credit card
  public static final int CLIENT_PAYMENT_PAYPAL = 2;
  public static final int CLIENT_PAYMENT_OTHER = 4; // NOT DEFINED


  // Stops Type
  public static final int ADDRESS_ROUTE_TYPE = 0;
  public static final int ADDRESS_CLIENT_TYPE = 1;


  // PriceAdjustment
  public enum PRICE_ADJUSTMENT_TYPE {
    AMOUNT(envConfig.getInt("constants.promo.type.amount")),
    PERCENTAGE(envConfig.getInt("constants.promo.type.percentge"));

    public final int val;

    PRICE_ADJUSTMENT_TYPE(int val) {
      this.val = val;
    }

    public int getValue() {
      return this.val;
    }
  }

  public enum TaskAction {
    Init(0),
    Working(1),
    End(2);
    private int val = 0;

    TaskAction(int val) {
      this.val = val;
    }

    public int getValue() {
      return this.val;
    }
  }

  public enum TypeTaskOps {
    Order(0),
    Service(1);
    private int val = 0;

    TypeTaskOps(int val) {
      this.val = val;
    }

    public int getValue() {
      return this.val;
    }
  }

  public static final int ORDER_STATUS_FINISHED = 1;

  // Property for getting the state of the typesafe config
  public static final String COM_IL_SOD_APPLICATION = "com.il.sod.application";

  // initialize all static information
  static {
  }

  public static int ACTION_INIT = 1;

}
