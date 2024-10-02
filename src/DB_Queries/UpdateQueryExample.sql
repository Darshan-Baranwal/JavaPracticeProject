UPDATE your_table_name
SET your_column_name = CASE
    WHEN your_column_name = 0 THEN your_column_name + 2
    WHEN your_column_name = 1 THEN your_column_name + 3
    ELSE your_column_name + 5
END;