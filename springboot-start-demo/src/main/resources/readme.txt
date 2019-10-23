环境准备：
springboot:2.1.6.RELEASE
mysql-java-connector:8.0.16
mysql数据库版本：5.7.22-log

需注意问题：
 1、连接数据库出现“The server time zone value  *&&…… is unrecogni”时，在url后面添加serverTimezone=UTC
    解决：jdbc:mysql://localhost:3306/db1?serverTimezone=UTC  (注意，一定是zone，不要写成Zone)
 2、连接数据库url写错时，“出现Driver com.mysql.jdbc.Driver claims to not accept jdbcUrl”。
    解决：jdbc://mysql://localhost:3306/db1?serverTimezone=UTC 改为
         jdbc:mysql://localhost:3306/db1?serverTimezone=UTC

 3、springboot配置多数据源
    由于我们需要配置多数据源，就需要自己写配置文件，如：JdbcDataSourceConfig,但是我们的配置会跟springboot自动配置的冲突，为此，有以下2种解决方案。
    方案1： 在启动类上排除“DataSourceAutoConfig、DataSourceTransactionManagerAutoConfiguration”.
    方案2：如果不在启动类上排除，则在自己写的配置文件中加入"@Primary"，表示以自己配置的数据源作为主要的数据源。