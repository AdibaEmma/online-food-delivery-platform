CREATE TABLE restaurants (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             address VARCHAR(255) NOT NULL
);

CREATE TABLE menu_items (
                            menu_item_id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            price DECIMAL(10, 2) NOT NULL,
                            restaurant_id INT NOT NULL,
                            FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       firstname VARCHAR(255) NOT NULL,
                       lastname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
                        order_id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        menu_item_id INT NOT NULL,
                        quantity INT NOT NULL,
                        order_status VARCHAR(255) NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(user_id),
                        FOREIGN KEY (menu_item_id) REFERENCES menu_items(menu_item_id)
);

CREATE TABLE user_orders (
                             user_id BIGINT REFERENCES users(user_id),
                             order_id BIGINT REFERENCES orders(order_id),
                             PRIMARY KEY (user_id, order_id)
);