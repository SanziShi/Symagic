SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `bookshop` ;
CREATE SCHEMA IF NOT EXISTS `bookshop` DEFAULT CHARACTER SET utf8 ;
DROP SCHEMA IF EXISTS `test` ;
CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 ;
USE `bookshop` ;

-- -----------------------------------------------------
-- Table `bookshop`.`BOOK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`BOOK` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`BOOK` (
  `BOOKID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '书籍ID,主键，非空，自增' ,
  `PICTURE` VARCHAR(255) NULL COMMENT '图书对应图片的存储路径,默认为null' ,
  `BOOKNAME` VARCHAR(100) NOT NULL COMMENT '书名，非空' ,
  `AUTHOR` VARCHAR(50) NOT NULL COMMENT '书的作者，非空' ,
  `PUBLISHER` VARCHAR(50) NOT NULL COMMENT '书籍出版社，非空' ,
  `PUBLISHDATE` DATE NOT NULL COMMENT '出版日期，年月日,默认为插入时间' ,
  `VERSION` TINYINT UNSIGNED NOT NULL COMMENT '书籍版次，默认为第一版' ,
  `PAGE` MEDIUMINT NOT NULL DEFAULT 0 COMMENT '书籍页数，默认为0' ,
  `BINDING` ENUM('精装','平装') NULL COMMENT '书籍的装帧，有两个选择（精装，平装）默认为NULL' ,
  `FOLIO` VARCHAR(10) NOT NULL DEFAULT '2' COMMENT '书的开本，默认为2开' ,
  `MARKETPRICE` DECIMAL(10,2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '书籍的价格,默认为0，小数点前8位，后2位' ,
  `DISCOUNT` FLOAT NOT NULL DEFAULT 100 COMMENT '书籍的折扣率，浮点数表示，最大值为1.0' ,
  `INVENTORY` MEDIUMINT UNSIGNED NULL DEFAULT 0 COMMENT '书籍库存量，默认为0' ,
  `BOOKDESC` VARCHAR(500) NULL COMMENT '书籍描述，可为空' ,
  `ISBN` VARCHAR(20) NOT NULL COMMENT '书籍的国际标准书号，20字节的长度，目前长度为13.' ,
  `OFFLINE` ENUM('下架','在架') NULL COMMENT '书籍状态，可能被下架，与库存为0有区别。两个选择，‘在架’、‘下架’' ,
  PRIMARY KEY (`BOOKID`) ,
  UNIQUE INDEX `ISBN_UNIQUE` (`ISBN` ASC) )
ENGINE = InnoDB
COMMENT = '书籍信息';


-- -----------------------------------------------------
-- Table `bookshop`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`USER` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`USER` (
  `USERID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID,唯一标示用户。' ,
  `USERNAME` VARCHAR(255) NOT NULL COMMENT '用户名，在此项目中时用户的邮箱地址。非空，且不能相同邮箱同时注册成功。' ,
  `NICKNAME` VARCHAR(255) NULL COMMENT '昵称' ,
  `SCORE` MEDIUMINT NULL DEFAULT 0 COMMENT '用户积分，交易成功后会获得一定的积分。默认为0' ,
  `QUESTION` VARCHAR(255) NOT NULL COMMENT '问题' ,
  `ANSWER` VARCHAR(40) NOT NULL COMMENT '问题答案' ,
  `REGISTEDATE` DATE NOT NULL ,
  PRIMARY KEY (`USERID`, `USERNAME`) ,
  UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookshop`.`COMMENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`COMMENT` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`COMMENT` (
  `USERNAME` VARCHAR(255) NOT NULL COMMENT '用户名，关联到user表中的username字段，也是这张表的主键。' ,
  `BOOKID` INT UNSIGNED NOT NULL COMMENT '书籍id,链接到book_info表的bookid字段，同样作为此表的主键。' ,
  `CONTENT` VARCHAR(500) NULL DEFAULT '' COMMENT '用户的评论信息，默认为“”' ,
  `RATING` VARCHAR(1) NOT NULL DEFAULT '1' COMMENT '用户对书的评论，共1,2,3,4,5个级别。' ,
  `COMMENTDATE` DATETIME NOT NULL COMMENT '评论日期，默认为插入时间。（包括年月日和时间）' ,
  PRIMARY KEY (`USERNAME`, `BOOKID`) ,
  INDEX `BOOKID` (`BOOKID` ASC) ,
  INDEX `B_USERNAME` (`USERNAME` ASC) ,
  CONSTRAINT `BOOKID`
    FOREIGN KEY (`BOOKID` )
    REFERENCES `bookshop`.`BOOK` (`BOOKID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `B_USERNAME`
    FOREIGN KEY (`USERNAME` )
    REFERENCES `bookshop`.`USER` (`USERNAME` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户对书的评论，只有购买过此书的用户才可以对书进行评论，且只能评论一次。所以USERID/BOOKID一起作为此表的主键' /* comment truncated */;


-- -----------------------------------------------------
-- Table `bookshop`.`BOOK_CATALOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`BOOK_CATALOG` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`BOOK_CATALOG` (
  `CATALOGID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，唯一的标识累分类。' ,
  `CATALOGNAME` VARCHAR(255) NOT NULL COMMENT '分类的名字,目前不去验证是否存在重复。' ,
  `LEVEL` VARCHAR(1) NOT NULL DEFAULT '1' COMMENT '分类级别，共分为两级，1：一级	2：二级，二选一' ,
  `UPID` INT NOT NULL DEFAULT 0 COMMENT '上一级目录id' ,
  `CATALOGDESC` VARCHAR(40) NULL COMMENT '目录描述信息，40字之内' ,
  PRIMARY KEY (`CATALOGID`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '用于书籍分类的分类列表。（可选分类）';


-- -----------------------------------------------------
-- Table `bookshop`.`FAVORITY_FOLDER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`FAVORITY_FOLDER` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`FAVORITY_FOLDER` (
  `FAVORITYID` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `USERNAME` VARCHAR(255) NOT NULL COMMENT '用户名，外键，连接到User表的username' ,
  `BOOKID` INT UNSIGNED NOT NULL COMMENT '收藏夹中对应书籍的ID号，链接到BOOK_INFO表的bookid' ,
  `ISBN` VARCHAR(20) NOT NULL ,
  `PUBLISHER` VARCHAR(50) NOT NULL COMMENT '出版社' ,
  `PUBLISHDATE` DATE NOT NULL COMMENT '出版日期' ,
  `MARKETPRICE` DECIMAL(10,2) UNSIGNED NOT NULL COMMENT '图书的市场价格（原价）' ,
  PRIMARY KEY (`FAVORITYID`) ,
  INDEX `F_USERNAME` (`USERNAME` ASC) ,
  CONSTRAINT `F_USERNAME`
    FOREIGN KEY (`USERNAME` )
    REFERENCES `bookshop`.`USER` (`USERNAME` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '收藏夹表，存储用户感兴趣的书籍基本信息。';


-- -----------------------------------------------------
-- Table `bookshop`.`BOOK_ORDER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`BOOK_ORDER` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`BOOK_ORDER` (
  `ORDERID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单号，唯一b标识一份订单.' ,
  `ORDERDATE` DATETIME NOT NULL COMMENT '订单日期' ,
  `ORDERSTATE` VARCHAR(1) NOT NULL DEFAULT 0 COMMENT '订单状态，目前有四种状态。0：已下单	1：已审核	2：交易成功	3：交易失败' ,
  `PAYMENT` VARCHAR(1) NULL DEFAULT 0 COMMENT '付款方式，目前只有一种方式，即 0:货到付款' ,
  `DELIVERYWAY` VARCHAR(1) NULL DEFAULT 0 COMMENT '送货方式，目前只有一种送货方式，即 0:快递' ,
  `USERNAME` VARCHAR(255) NOT NULL COMMENT '用户id关联到用户表的userid.' ,
  `RECEIVERNAME` VARCHAR(255) NOT NULL COMMENT '收件人姓名' ,
  `ADDRDETAIL` VARCHAR(512) NOT NULL COMMENT '所有有关地址的信息字符串,xml格式' ,
  `ZIPCODE` VARCHAR(10) NOT NULL COMMENT '收获地址的邮政编码' ,
  `PHONENUM` VARCHAR(20) NULL COMMENT '座机号' ,
  `MOBILENUM` VARCHAR(20) NULL COMMENT '手机号' ,
  `TOTALPRICE` FLOAT UNSIGNED NOT NULL COMMENT '商品总价' ,
  `SCORE` INT NOT NULL ,
  PRIMARY KEY (`ORDERID`) ,
  INDEX `O_USERNAME` (`USERNAME` ASC) ,
  UNIQUE INDEX `ORDERDATE_UNIQUE` (`ORDERDATE` ASC) ,
  CONSTRAINT `O_USERNAME`
    FOREIGN KEY (`USERNAME` )
    REFERENCES `bookshop`.`USER` (`USERNAME` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '订单表，存储订单相关信息';


-- -----------------------------------------------------
-- Table `bookshop`.`ORDER_DETAIL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`ORDER_DETAIL` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`ORDER_DETAIL` (
  `ORDERID` INT UNSIGNED NOT NULL COMMENT '订单id，关联到order表的orderid.' ,
  `BOOKID` INT UNSIGNED NOT NULL COMMENT '书籍ID' ,
  `ISBN` VARCHAR(20) NOT NULL COMMENT '国际标准图书号，用来唯一标识一本书。' ,
  `BOOKNAME` VARCHAR(100) NOT NULL COMMENT '书名' ,
  `MARKETPRICE` DECIMAL(10,2) NOT NULL COMMENT '市场价格，也就是原价' ,
  `DISCOUNT` FLOAT NOT NULL COMMENT '折扣率，实际价格要通过MARKETPRICE*DISCOUNT得到。' ,
  `AMOUNT` MEDIUMINT NOT NULL DEFAULT 1 COMMENT '此订单中包含此书的数目' ,
  INDEX `ORDERID` (`ORDERID` ASC) ,
  CONSTRAINT `ORDERID`
    FOREIGN KEY (`ORDERID` )
    REFERENCES `bookshop`.`BOOK_ORDER` (`ORDERID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '订单的详细信息，即包括订单中的每一件商品的信息。';


-- -----------------------------------------------------
-- Table `bookshop`.`SECRET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`SECRET` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`SECRET` (
  `USERID` INT UNSIGNED NOT NULL COMMENT '用户ID,链接到USER表的USERID字段。同样是此表的主键。' ,
  `PASSWORD` VARCHAR(32) NOT NULL COMMENT '用户密码，是将用户输入的密码进行md5加密后得到的密文，32bit 长。' ,
  PRIMARY KEY (`USERID`) ,
  INDEX `S_USERID` (`USERID` ASC) ,
  CONSTRAINT `S_USERID`
    FOREIGN KEY (`USERID` )
    REFERENCES `bookshop`.`USER` (`USERID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户密码表，存储用户密码md5加密后的32bit数';


-- -----------------------------------------------------
-- Table `bookshop`.`USER_ADDR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`USER_ADDR` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`USER_ADDR` (
  `ADDRID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '直至ID,唯一的标识一条地址。' ,
  `USERNAME` VARCHAR(255) NOT NULL COMMENT '用户名,外键。' ,
  `RECEIVERNAME` VARCHAR(255) NOT NULL COMMENT '收件人姓名' ,
  `ADDRDETAIL` VARCHAR(512) NOT NULL COMMENT '送货地址的详细信息.xml格式' ,
  `ZIPCODE` VARCHAR(10) NOT NULL COMMENT '收件地址的邮政编码。' ,
  `PHONENUM` VARCHAR(20) NULL COMMENT '收件人座机号' ,
  `MOBILENUM` VARCHAR(20) NULL COMMENT '收件人手机号。' ,
  PRIMARY KEY (`ADDRID`) ,
  INDEX `U_USERNAME` (`USERNAME` ASC) ,
  CONSTRAINT `U_USERNAME`
    FOREIGN KEY (`USERNAME` )
    REFERENCES `bookshop`.`USER` (`USERNAME` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '用户预存的地址信息';


-- -----------------------------------------------------
-- Table `bookshop`.`ADMIN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`ADMIN` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`ADMIN` (
  `ADMINID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '管理员id字段，主键，唯一标示一位管理员。' ,
  `ADMINNAME` VARCHAR(255) NOT NULL COMMENT '管理员用户名。' ,
  `PASSWORD` VARCHAR(40) NOT NULL COMMENT '管理员的密码，存储的事通过md5加密后的密文。32bit长。' ,
  PRIMARY KEY (`ADMINID`) ,
  UNIQUE INDEX `ADMINNAME_UNIQUE` (`ADMINNAME` ASC) )
ENGINE = InnoDB
COMMENT = '管理员信息表';


-- -----------------------------------------------------
-- Table `bookshop`.`BOOK_CATALOG_DETAIL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`BOOK_CATALOG_DETAIL` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`BOOK_CATALOG_DETAIL` (
  `BOOKID` INT UNSIGNED NOT NULL COMMENT '书籍id,关联到book_info表的bookid.' ,
  `CATALOGID` INT UNSIGNED NOT NULL COMMENT '分类id,关联到book_catergory表的catergoryid' ,
  PRIMARY KEY (`BOOKID`, `CATALOGID`) ,
  INDEX `C_BOOKID` (`BOOKID` ASC) ,
  INDEX `C_CATERGORYID` (`CATALOGID` ASC) ,
  CONSTRAINT `C_BOOKID`
    FOREIGN KEY (`BOOKID` )
    REFERENCES `bookshop`.`BOOK` (`BOOKID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `C_CATERGORYID`
    FOREIGN KEY (`CATALOGID` )
    REFERENCES `bookshop`.`BOOK_CATALOG` (`CATALOGID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookshop`.`DISTRICT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`DISTRICT` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`DISTRICT` (
  `id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，id' ,
  `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '所标识区域名。' ,
  `level` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '1 2 3 4' ,
  `usetype` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否可用，目前默认为0，不考虑。' ,
  `upid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0' COMMENT '该区域的上一级区域id' ,
  `displayorder` SMALLINT(6) NOT NULL DEFAULT '0' COMMENT '不考虑' ,
  PRIMARY KEY (`id`) ,
  INDEX `upid` (`upid` ASC, `displayorder` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = MyISAM
AUTO_INCREMENT = 45052
DEFAULT CHARACTER SET = utf8
COMMENT = '地区表，国家、省、市、区';


-- -----------------------------------------------------
-- Table `bookshop`.`CART`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`CART` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`CART` (
  `USERNAME` VARCHAR(255) NOT NULL COMMENT '外键，关联USER表的USERNAME' ,
  `ADDDATE` DATE NOT NULL COMMENT '添加购物车时间' ,
  `BOOKID` INT UNSIGNED NOT NULL COMMENT '书籍ID' ,
  `AMOUNT` INT UNSIGNED NOT NULL COMMENT '书籍商品数量' ,
  INDEX `C_USERNAME` (`USERNAME` ASC) ,
  INDEX `CART_BOOKID` (`BOOKID` ASC) ,
  CONSTRAINT `C_USERNAME`
    FOREIGN KEY (`USERNAME` )
    REFERENCES `bookshop`.`USER` (`USERNAME` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CART_BOOKID`
    FOREIGN KEY (`BOOKID` )
    REFERENCES `bookshop`.`BOOK` (`BOOKID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookshop`.`SCORE_LEVEL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookshop`.`SCORE_LEVEL` ;

CREATE  TABLE IF NOT EXISTS `bookshop`.`SCORE_LEVEL` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '等级ID,主键，唯一标示一级' ,
  `name` VARCHAR(50) NOT NULL COMMENT '等级名,来描述一类顶级' ,
  `lowlimit` INT NOT NULL COMMENT '等级下界' ,
  `uplimit` INT NOT NULL COMMENT '等级上级' ,
  `rate` FLOAT NOT NULL COMMENT '倍率' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;

USE `test` ;

-- -----------------------------------------------------
-- Table `test`.`district`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`district` ;

CREATE  TABLE IF NOT EXISTS `test`.`district` (
  `id` MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL DEFAULT '' ,
  `level` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' ,
  `usetype` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' ,
  `upid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0' ,
  `displayorder` SMALLINT(6) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`id`) ,
  INDEX `upid` (`upid` ASC, `displayorder` ASC) )
ENGINE = MyISAM
AUTO_INCREMENT = 45052
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
