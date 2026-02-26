create procedure place_order (
IN p_user_id INT,
IN p_amount INT,

OUTPUT o_order_id       INT,
OUTPUT o_status         VARCHAR(20)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET o_order_id = NULL;
        SET o_status = 'FAILED';
    END;

    START TRANSACTION;

    INSERT INTO orders(user_id, amount, idempotency_key, status)
    VALUES (p_user_id, p_amount, p_idempotency_key, 'CREATED');

    SET o_order_id = LAST_INSERT_ID();
    SET o_status   = 'SUCCESS';

    COMMIT;
END;

CALL place_order(
  101,
  250.00,
  'req-123',
  @order_id,
  @status
);

SELECT @order_id, @status;


---------- Rollback from a certain point


INSERT INTO orders (...) ;
SAVEPOINT after_order;

INSERT INTO payments (...) ;
-- fails

ROLLBACK TO after_order;