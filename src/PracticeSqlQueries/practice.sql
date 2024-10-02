create PROCEDURE MYProcedure
@Country varchar(50) = NULL
AS
BEGIN
    SELECT CUSTOMERNAME, CONTACTNAME
    FROM CUSTOMERS
    WHERE Country = @Country;
END;

EXEC MYProcedure @Country = "INDIA";


create PROCEDURE MYCountryCount
@Country varchar(50) = NULL, @Count INT OUTPUT
AS
BEGIN
    SELECT @COUNT = COUNT(*)
    FROM CUSTOMERS
    WHERE Country = @Country;

    DECLARE @COUNT int
END;

EXEC MYCountryCount @Country = "INDIA";


