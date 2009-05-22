package liquibase.sqlgenerator;

import liquibase.statement.GetViewDefinitionStatement;
import liquibase.database.Database;
import liquibase.database.DB2Database;
import liquibase.database.MSSQLDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.exception.JDBCException;
import liquibase.exception.UnexpectedLiquibaseException;

public class GetViewDefinitionGeneratorMSSQL extends GetViewDefinitionGenerator {
    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(GetViewDefinitionStatement statement, Database database) {
        return database instanceof MSSQLDatabase;
    }

    @Override
    public Sql[] generateSql(GetViewDefinitionStatement statement, Database database) {
        try {
            String sql = "select view_definition from INFORMATION_SCHEMA.VIEWS where upper(table_name)='" + statement.getViewName().toUpperCase() + "'";
//        if (StringUtils.trimToNull(schemaName) != null) {
            sql += " and table_schema='" + database.convertRequestedSchemaToSchema(statement.getSchemaName()) + "'";
            sql += " and table_catalog='" + database.getDefaultCatalogName() + "'";
//        }

//        log.info("GetViewDefinitionSQL: "+sql);

            return new Sql[]{
                    new UnparsedSql(sql)
            };
        } catch (JDBCException e) {
            throw new UnexpectedLiquibaseException(e);
        }
    }
}