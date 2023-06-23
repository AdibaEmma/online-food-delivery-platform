CREATE TABLE restaurants (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             address VARCHAR(255) NOT NULL
);

CREATE TABLE menu_items (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            price DECIMAL(10, 2) NOT NULL,
                            restaurant_id INT NOT NULL,
                            FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        menu_item_id INT NOT NULL,
                        quantity INT NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);