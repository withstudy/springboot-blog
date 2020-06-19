/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 5.6.26 : Database - node
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`node` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `node`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`content`) values (1,'js','456'),(4,'html','html 前端基础语言'),(5,'vue','vue国内比较流行的框架'),(6,'react','前端三大框架之一，vue，angluar'),(7,'node','js后端开发语言，方法搭建服务器');

/*Table structure for table `comments` */

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `con_id` int(11) NOT NULL,
  `comments` text COLLATE utf8_bin,
  `addTime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `con_id` (`con_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `comments` */

insert  into `comments`(`id`,`con_id`,`comments`,`addTime`,`username`) values (15,13,'Vue 采用自底向上增量开发的设计','2019-11-26','admin'),(16,14,'代码逻辑却非常简单','2019-11-26 ','admin'),(17,15,'66666666666666','2020-06-10','admin'),(18,15,'66666666666666','2020-06-10','admin'),(19,15,'66666666666666','2020-06-10','admin'),(20,15,'66666666666666','2020-06-10','admin'),(21,15,'66666666666666','2020-06-10','admin'),(22,15,'66666666666666','2020-06-10','admin');

/*Table structure for table `content` */

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin,
  `content` text COLLATE utf8_bin,
  `views` int(11) DEFAULT '0',
  `addTime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `comments` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `c_id` (`c_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `c_id` FOREIGN KEY (`c_id`) REFERENCES `category` (`id`),
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `content` */

insert  into `content`(`id`,`c_id`,`u_id`,`title`,`description`,`content`,`views`,`addTime`,`comments`) values (13,5,26,'vue','Vue.js（读音 /vjuː/, 类似于 view） 是一套构建用户界面的渐进式框架。','Vue.js是一套构建用户界面的渐进式框架。与其他重量级框架不同的是，Vue 采用自底向上增量开发的设计。Vue 的核心库只关注视图层，并且非常容易学习，非常容易与其它库或已有项目整合。另一方面，Vue 完全有能力驱动采用单文件组件和Vue生态系统支持的库开发的复杂单页应用。\r\nVue.js 的目标是通过尽可能简单的 API 实现响应的数据绑定和组合的视图组件 [2]  。\r\nVue.js 自身不是一个全能框架——它只聚焦于视图层。因此它非常容易学习，非常容易与其它库或已有项目整合。另一方面，在与相关工具和支持库一起使用时 [3]  ，Vue.js 也能完美地驱动复杂的单页应用。\r\n',5,'2020-01-21',1),(14,6,26,'react','React 起源于 Facebook 的内部项目，因为该公司对市场上所有 JavaScript MVC 框架，都不满意，就决定自己写一套，用来架设Instagram 的网站。做出来以后，发现这套东西很好用，就在2013年5月开源了。','由于 React的设计思想极其独特，属于革命性创新，性能出众，代码逻辑却非常简单。所以，越来越多的人开始关注和使用，认为它可能是将来 Web 开发的主流工具。\r\n这个项目本身也越滚越大，从最早的UI引擎变成了一整套前后端通吃的 Web App 解决方案。衍生的 React Native 项目，目标更是宏伟，希望用写 Web App 的方式去写 Native App。如果能够实现，整个互联网行业都会被颠覆，因为同一组人只需要写一次 UI ，就能同时运行在服务器、浏览器和手机。\r\nReact主要用于构建UI。你可以在React里传递多种类型的参数，如声明代码，帮助你渲染出UI、也可以是静态的HTML DOM元素、也可以传递动态变量、甚至是可交互的应用组件。',5,'2020-01-21',1),(15,7,26,'node','Node.js 是一个基于 Chrome V8 引擎的 JavaScript 运行环境。 Node.js 使用了一个事件驱动、非阻塞式 I/O 的模型。','V8引擎本身使用了一些最新的编译技术。这使得用Javascript这类脚本语言编写出来的代码运行速度获得了极大提升，又节省了开发成本。对性能的苛求是Node的一个关键因素。 Javascript是一个事件驱动语言，Node利用了这个优点，编写出可扩展性高的服务器。Node采用了一个称为“事件循环(event loop）”的架构，使得编写可扩展性高的服务器变得既容易又安全。提高服务器性能的技巧有多种多样。Node选择了一种既能提高性能，又能减低开发复杂度的架构。这是一个非常重要的特性。并发编程通常很复杂且布满地雷。Node绕过了这些，但仍提供很好的性能。',5,'2020-01-21',0),(16,1,26,'js','avaScript（简称“JS”） 是一种具有函数优先的轻量级，解释型或即时编译型的高级编程语言。虽然它是作为开发Web页面的脚本语言而出名的，但是它也被用到了很多非浏览器环境中，JavaScript 基于原型编程、多范式的动态脚本语言，并且支持面向对象、命令式和声明式（如函数式编程）风格','JavaScript是一种属于网络的高级脚本语言,已经被广泛用于Web应用开发,常用来为网页添加各式各样的动态功能,为用户提供更流畅美观的浏览效果。通常JavaScript脚本是通过嵌入在HTML中来实现自身的功能的。 [5] \r\n是一种解释性脚本语言（代码不进行预编译）。 [6] \r\n主要用来向HTML（标准通用标记语言下的一个应用）页面添加交互行为。 [6] \r\n可以直接嵌入HTML页面，但写成单独的js文件有利于结构和行为的分离。 [6] \r\n跨平台特性，在绝大多数浏览器的支持下，可以在多种平台下运行（如Windows、Linux、Mac、Android、iOS等）。\r\nJavascript脚本语言同其他语言一样，有它自身的基本数据类型，表达式和算术运算符及程序的基本程序框架。Javascript提供了四种基本的数据类型和两种特殊数据类型用来处理数据和文字。而变量提供存放信息的地方，表达式则可以完成较复杂的信息处理。\r\n可以实现web页面的人机交互。',5,'2020-01-21',0),(19,4,29,'html','HTML称为超文本标记语言，是一种标识性的语言。它包括一系列标签．通过这些标签可以将网络上的文档格式统一，使分散的Internet资源连接为一个逻辑整体。HTML文本是由HTML命令组成的描述性文本，HTML命令可以说明文字，图形、动画、声音、表格、链接等。',' HTML的英文全称是 Hyper Text Marked Language，即超文本标记语言。HTML是由Web的发明者 Tim Berners-Lee和同事 Daniel W. Connolly于1990年创立的一种标记语言，它是标准通用化标记语言SGML的应用。用HTML编写的超文本文档称为HTML文档，它能独立于各种操作系统平台(如UNIX， Windows等)。使用HTML语言，将所需要表达的信息按某种规则写成HTML文件，通过专用的浏览器来识别，并将这些HTML文件“翻译”成可以识别的信息，即现在所见到的网页。',0,'2020-06-05 17:20:16',0),(20,1,26,'html','全俄文请问请问请问恶趣味请问','  去问我去饿请问请问e\'q\'w',0,'2020-06-12',0);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `permission` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `permission` */

insert  into `permission`(`id`,`u_id`,`permission`) values (1,26,'user:update'),(2,26,'user:create'),(3,26,'user:delete'),(4,26,'user:select'),(5,26,'content:update'),(6,26,'gg:upload'),(7,26,'content:delete'),(8,26,'content:select'),(9,26,'content:create'),(10,26,'category:update'),(11,26,'category:delete'),(12,26,'category:create'),(13,26,'category:select'),(14,26,'comments:update'),(15,26,'comments:delete'),(16,26,'comments:create'),(17,26,'comments:delete'),(18,26,'gg:download');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) COLLATE utf8_bin NOT NULL,
  `password` char(255) COLLATE utf8_bin NOT NULL,
  `isAdmin` tinyint(1) DEFAULT '0',
  `img` varchar(255) COLLATE utf8_bin DEFAULT '20f34f52073beb6212b34f9d32443213.jpeg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`isAdmin`,`img`) values (23,'xhb','xhb',0,'20f34f52073beb6212b34f9d32443213.jpeg'),(26,'admin','3ef7164d1f6167cb9f2658c07d3c2f0a',1,'2020061310222945.png'),(29,'4520','4520',1,'20f34f52073beb6212b34f9d32443213.jpeg'),(30,'cl','cdl',1,'20f34f52073beb6212b34f9d32443213.jpeg'),(31,'admin2','ce5251c17c574dd187005ffc1bb0a575',0,'20f34f52073beb6212b34f9d32443213.jpeg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
