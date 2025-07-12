INSERT INTO article (id, name, price) VALUES
    ('843362c3-5157-4a11-95e7-d8490742aa50', 'T-shirt', 16.0),
    ('d75b0690-a896-4599-9e29-8c67c69e9612', 'Sweatshirt', 21.0),
    ('f9a542fb-02f9-42df-b1ac-b278b237efdb', 'Jogging Pants', 29.0),
    ('514f2c76-30fa-4858-9261-8f52d1a78ff3', 'Jacket', 49.99),
    ('38fc3692-c1f3-48b1-89a9-7fdc13a778a5', 'Polo', 22.0),
    ('b362359e-a1e6-47ba-89c0-877dbdff59d0', 'Shorts', 18.0),
    ('f0022b4c-87e3-48b5-a81a-ff8162d1c00b', 'Hoodie', 35.0),
    ('51f4dad9-fb34-41c1-9f1c-4f9d6ddf3e7e', 'Raincoat', 42.0),
    ('31230dd1-f6bf-44d6-bb89-6b38f0914b17', 'Jeans', 39.0),
    ('d012531d-fe33-46a0-800b-676f02acbb24', 'Sneakers', 59.0);

INSERT INTO article_item (id, article_id, size, color, available_stock, image_url) VALUES
    -- T-shirt
        ('a89ac064-8a53-4289-87f9-1b004b3f4826', '843362c3-5157-4a11-95e7-d8490742aa50', 'S', 'Red', 3, 'https://preview.free3d.com/img/2017/03/2174875367456638608/463t4pzq.jpg'),
        ('2fe84a96-391a-4236-a549-1b9c0d95369e', '843362c3-5157-4a11-95e7-d8490742aa50', 'M', 'Red', 3, 'https://preview.free3d.com/img/2017/03/2174875367456638608/463t4pzq.jpg'),
        ('5a3a7900-694f-4506-adf8-98cc3a8d3368', '843362c3-5157-4a11-95e7-d8490742aa50', 'L', 'Red', 3, 'https://preview.free3d.com/img/2017/03/2174875367456638608/463t4pzq.jpg'),
        ('3014d075-6618-4598-afc8-6ba09e2f8c5e', '843362c3-5157-4a11-95e7-d8490742aa50', 'XL', 'Red', 3, 'https://preview.free3d.com/img/2017/03/2174875367456638608/463t4pzq.jpg'),
        ('29380101-d347-4f5a-a107-4cf2a1c7cb43', '843362c3-5157-4a11-95e7-d8490742aa50', 'M', 'Blue', 2, 'https://preview.free3d.com/img/2017/03/1851631122866242917/9f9th0vx.jpg'),

        -- Sweatshirt
        ('c9d04f05-864d-4225-b940-871151a557d1', 'd75b0690-a896-4599-9e29-8c67c69e9612', 'S', 'Blue', 5, 'https://www.patine.fr/cdn/shop/files/marty90crewneck_bleumarine_1.jpg?v=1684416832'),
        ('1b5918a1-6c2c-48bf-84ff-9fd6adaa2229', 'd75b0690-a896-4599-9e29-8c67c69e9612', 'XL', 'Green', 5, 'https://d1l2kcmc130e06.cloudfront.net/3/images/colors_240x300/gildan-18000-forest-green.jpg'),

        -- Jogging Pants
        ('7a46b8b1-e6ac-402d-a467-b24dcb6cdebb', 'f9a542fb-02f9-42df-b1ac-b278b237efdb', 'M', 'Black', 7, 'https://www.imii.com/_next/image?url=https%3A%2F%2Fcdn.shopify.com%2Fs%2Ffiles%2F1%2F0835%2F3497%2F1159%2Ffiles%2FLOU02SWEATPANT_3_3.png%3Fv%3D1722614976&w=2048&q=75'),
        ('d978801b-725b-4589-826d-36d0458db1cb', 'f9a542fb-02f9-42df-b1ac-b278b237efdb', 'L', 'Grey', 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfSnjgtBMqEwWkm0j6HsmFIRknDpB-4xD4uA&s'),

        -- Jacket
        ('604c3514-5d82-426f-8835-2acbfec51e05', '514f2c76-30fa-4858-9261-8f52d1a78ff3', 'M', 'Blue', 2, 'https://www.angeljackets.com/product_images/m/041/mens_hooded_jacket_blue__16132_zoom.webp'),
        ('3af56eee-e643-41a8-896b-7ab3d3414e22', '514f2c76-30fa-4858-9261-8f52d1a78ff3', 'L', 'Black', 1, 'https://www.angeljackets.com/product_images/c/719/bomber_black_leather_hooded_jacket__84293_zoom.webp'),

        -- Polo
        ('7d27cf9e-c53c-4ffc-ac09-30e472cb5d40', '38fc3692-c1f3-48b1-89a9-7fdc13a778a5', 'L', 'White', 4, 'https://p.turbosquid.com/ts-thumb/13/PawmpH/5e/000/jpg/1679225261/600x600/fit_q87/86f72842ddf15204c30caf678127c41452fd4866/000.jpg'),
        ('138bd1e0-aee7-4520-b24a-3a6567c4cb9a', '38fc3692-c1f3-48b1-89a9-7fdc13a778a5', 'M', 'Green', 5, 'https://public.blenderkit.com/thumbnails/assets/712334fb5efe41e6bd6294266e9ec445/files/thumbnail_b34afb2d-af77-45e1-afcf-a3ed62d3cc6c.jpg.256x256_q85.jpg.webp?webp_generated=1667255150'),

        -- Shorts
        ('a2a8a08a-3b80-4732-9efd-8c976a5ad968', 'b362359e-a1e6-47ba-89c0-877dbdff59d0', 'S', 'Black', 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGsb8KwAh8HAoKAusI3sWLF06ZMIt9RBhtLg&s'),
        ('a3411102-32c9-4256-80a6-2a4191683da7', 'b362359e-a1e6-47ba-89c0-877dbdff59d0', 'M', 'Beige', 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQF8G3SMMTnzUYFGlv3p0vrfBEkKi-MxRhd0Q&s'),

        -- Hoodie
        ('4c70f262-8ed9-4055-9436-87aa88ff8585', 'f0022b4c-87e3-48b5-a81a-ff8162d1c00b', 'L', 'Grey', 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLMWleMnhaTzLIv8gpnYSxxy3V7bAS93iWdg&s'),
        ('3a95b70a-0406-41c5-8f47-b92033b5f65a', 'f0022b4c-87e3-48b5-a81a-ff8162d1c00b', 'XL', 'Black', 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlOE7QM-ZdtOqST6iTaa7c1BRj-Bzf1bI5qg&s'),

        -- Raincoat
        ('8a73a71c-8ebc-496f-8a9c-28acdc3261f8', '51f4dad9-fb34-41c1-9f1c-4f9d6ddf3e7e', 'L', 'Yellow', 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT65LXEIa0DJIXLMFtDjQmIVuzhYmgJJryigg&s'),

        -- Jeans
        ('d6ab7ee0-6ef8-400f-8e4b-4be3168cedda', '31230dd1-f6bf-44d6-bb89-6b38f0914b17', '32', 'Blue', 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShC3Uc_uUQsrUVz-X5GsRMbg1bRVIQGZ-WuQ&s'),
        ('6acab4a9-5451-4a7c-aac8-045a8a96314f', '31230dd1-f6bf-44d6-bb89-6b38f0914b17', '34', 'Black', 4, 'https://cdn.skatedeluxe.com/thumb/raXYZDq0PIsjjPEFkCx9LosKUno=/fit-in/600x700/filters:fill(white):brightness(-4)/product/166471-0-DC-WorkerBaggy.jpg'),

        -- Sneakers
        ('b88de3d7-5b2a-421d-bbc9-878ac99da064', 'd012531d-fe33-46a0-800b-676f02acbb24', '42', 'White', 6, 'https://m.media-amazon.com/images/I/61DffHsw9DL._UY900_.jpg'),
        ('6eb9cdcb-d070-42ce-abfb-09686e0b819a', 'd012531d-fe33-46a0-800b-676f02acbb24', '43', 'Black', 4, 'https://m.media-amazon.com/images/I/61vLWCjGFbL._UY900_.jpg');


INSERT INTO customer (id, firstname, lastname, email)
    VALUES ('306c70bd-c6cf-4e5b-a8fe-aed3b37d5a61', 'Alice', 'Adams', 'aa@a.a'),
           ('41487c0c-0a2d-48e1-8b7e-75c528e08d1c', 'Bob', 'Brown', 'bb@b.b');

INSERT INTO cart (id, customer_id, created_at)
    VALUES ('8da92904-0ab6-46ec-8da4-2306445e6be5', '306c70bd-c6cf-4e5b-a8fe-aed3b37d5a61', CURRENT_TIMESTAMP);

INSERT INTO cart_item (id, cart_id, article_item_id, quantity, created_at)
    VALUES ('a853ba3e-dc90-4e81-93d3-5d16af5a8af3', '8da92904-0ab6-46ec-8da4-2306445e6be5', 'a89ac064-8a53-4289-87f9-1b004b3f4826', 1, CURRENT_TIMESTAMP),
           ('5f36c855-3123-47da-a3b9-0a631be9df99', '8da92904-0ab6-46ec-8da4-2306445e6be5', '7a46b8b1-e6ac-402d-a467-b24dcb6cdebb', 2, CURRENT_TIMESTAMP);

