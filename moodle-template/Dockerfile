FROM php:7.2-apache

ARG USER_ID="1000"
ENV APACHE_USER_ID "${USER_ID}"

COPY etc/apache/php.ini /usr/local/etc/php/

RUN apt-get update \
    && apt-get install -y --no-install-recommends gettext libcurl4-openssl-dev \
    libpq-dev libxslt-dev \
    libxml2-dev libicu-dev libfreetype6-dev libjpeg62-turbo-dev libmemcached-dev \
    zlib1g-dev unixodbc-dev \
    locales libaio1 libcurl4 libgss3 libpq5 \
    libmemcached11 libmemcachedutil2 libxml2 libxslt1.1 unixodbc \
    libmcrypt-dev \
    unzip ghostscript locales apt-transport-https
    
RUN echo 'en_US.UTF-8 UTF-8' > /etc/locale.gen \
    && echo 'es_CO.UTF-8 UTF-8' >> /etc/locale.gen \
    && locale-gen

RUN docker-php-ext-install -j$(nproc) \
    intl \
    mysqli \
    pdo \
    pdo_mysql \
    opcache \
    pgsql \
    soap \
    xsl \
    xmlrpc \
    zip \
    && docker-php-ext-configure gd --with-freetype-dir=/usr/include/ --with-jpeg-dir=/usr/include/ \
    && docker-php-ext-install -j$(nproc) gd

RUN pecl install solr memcached redis apcu igbinary \
    && docker-php-ext-enable solr memcached redis apcu igbinary

RUN echo 'apc.enable_cli = On' >> /usr/local/etc/php/conf.d/docker-php-ext-apcu.ini

RUN pecl clear-cache \
    && apt-get remove --purge -y gettext libcurl4-openssl-dev libpq-dev \
        libxslt-dev \
        libxml2-dev libfreetype6-dev libjpeg62-turbo-dev libmemcached-dev \
        zlib1g-dev libpng12-dev unixodbc-dev \
    && apt-get autoremove -y \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN usermod -u ${USER_ID} www-data
# COPY moodle /var/www/html/
RUN chown -R ${APACHE_USER_ID}.${APACHE_USER_ID} /var/www/html/

RUN mkdir /var/www/moodledata && chown ${APACHE_USER_ID}.${APACHE_USER_ID} /var/www/moodledata && \
  mkdir /var/www/temp && chown ${APACHE_USER_ID}.${APACHE_USER_ID} /var/www/temp && \
  mkdir /var/www/cache && chown ${APACHE_USER_ID}.${APACHE_USER_ID} /var/www/cache
  
RUN chmod 777 /tmp && chmod +t /tmp
EXPOSE 80