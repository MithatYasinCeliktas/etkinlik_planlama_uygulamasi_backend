# Etkinlik Planlama Uygulaması - Backend

## 📋 Proje Açıklaması

Bu proje, etkinliklerin yönetimi ve planlanması için tasarlanmış bir backend uygulamasıdır. Kullanıcılar etkinlikleri oluşturabilir, düzenleyebilir, silebilir ve etkinliklere katılabilirler. Spring Boot ve RESTful API mimarisini kullanarak modern ve güvenilir bir hizmet sağlamaktadır.

## 🛠️ Kullanılan Teknolojiler

### Backend
- **Java 26** - Programlama dili
- **Spring Boot 4.0.6** - Web framework
- **Spring Data JPA** - ORM ve veritabanı işlemleri
- **Spring Validation** - Form validasyonu
- **Spring Security Test** - Güvenlik testleri
- **Spring Boot Actuator** - Uygulama monitoring

### Veritabanı
- **H2 Database** - In-memory veritabanı (geliştirme ve test için)

### Ek Kütüphaneler
- **SpringDoc OpenAPI 3.0.2** - Swagger/OpenAPI dokümantasyonu
- **Lombok** - Kod geliştirme
- **BCrypt** - Şifre hashing
- **ModelMapper 3.2.0** - DTO mapping
- **Maven** - Proje yönetimi

## 📦 Kurulum Adımları

### Gereksinimler
- Java 26 veya üzeri
- Maven 3.6+
- Git

### 1. Repository'yi Clone Edin

```bash
git clone https://github.com/MithatYasinCeliktas/etkinlik_planlama_uygulamasi_backend.git
cd etkinlik_planlama_uygulamasi_backend/etkinlik_planlama_uygulamasi_backend
```

### 2. Bağımlılıkları İndirin

```bash
mvn clean install
```

## 🚀 Backend Çalıştırma

### Maven ile Çalıştırma

```bash
# Proje kök dizininden
mvn spring-boot:run
```

Uygulama varsayılan olarak **http://localhost:8085** adresinde başlatılacaktır.

### IDE ile Çalıştırma (IntelliJ IDEA / Eclipse)

1. Projeyi IDE'de açın
2. `etkinlik_planlama_uygulamasi_backend` modülünü seçin
3. Ana dosyayı bulun ve çalıştırın
4. Veya Run konfigürasyonundan Spring Boot uygulamasını başlatın

### JAR Dosyası Oluşturma ve Çalıştırma

```bash
# JAR dosyası oluştur
mvn clean package

# JAR dosyasını çalıştır
java -jar target/etkinlik_planlama_uygulamasi_backend-0.0.1-SNAPSHOT.jar
```

## 📱 Frontend Çalıştırma

Frontend uygulaması ayrı bir repository'de yer almaktadır. Frontend kurulumu için:

1. Frontend repository'sini clone edin
2. İlgili README dosyasındaki talimatları takip edin
3. Frontend uygulaması backend API'si ile bağlanacaktır (varsayılan: http://localhost:8085)

**Not:** Frontend ve backend'in aynı cihazda çalışırken CORS ayarlamaları yapılması gerekebilir.

## 📚 Swagger (OpenAPI) Dokümantasyonu

API dokümantasyonuna erişmek için aşağıdaki URL'yi kullanın:

- **Swagger UI:** http://localhost:8085/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8085/v3/api-docs

Swagger UI'da tüm API endpoint'lerini görebilir ve test edebilirsiniz.

## 💾 Veritabanı Bilgileri

### H2 Veritabanı

Proje geliştirme ve test amaçlı olarak **H2 in-memory veritabanı** kullanmaktadır.

**H2 Console Erişimi:**

```
URL: http://localhost:8085/h2-console
```

**Bağlantı Ayarları:**
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Driver Class:** `org.h2.Driver`
- **Kullanıcı Adı:** `sa`
- **Şifre:** (boş)

### application.properties Yapılandırması

`src/main/resources/application.properties` dosyasında veritabanı ayarları yapılabilir:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### JPA / Hibernate Konfigürasyonu

- **ORM:** Spring Data JPA + Hibernate
- **Entity Yönetimi:** Otomatik DDL (create, update, validate)
- **Mapping:** ModelMapper kullanılarak DTO dönüşümleri

## 🔒 Güvenlik

- **Şifre Hashing:** BCrypt algoritması kullanılmaktadır
- **Validasyon:** Spring Validation ile form validasyonu
- **CORS:** Gerekli CORS ayarlamaları yapılabilir

## 📝 API Endpoint Örnekleri

Swagger UI'dan detaylı bilgi alabilirsiniz, ancak temel endpoint'ler:

```
GET    /api/events              - Tüm etkinlikleri listele
POST   /api/events              - Yeni etkinlik oluştur
GET    /api/events/{id}         - Belirli bir etkinliği getir
PUT    /api/events/{id}         - Etkinliği güncelle
DELETE /api/events/{id}         - Etkinliği sil
```

## 🧪 Test Etme

```bash
# Testleri çalıştır
mvn test
```

## 📞 Destek ve İletişim

Herhangi bir sorun veya soru için GitHub Issues bölümünde bir issue açabilirsiniz.

## 📄 Lisans

Bu proje akademik amaçlı bir proje ödevi olarak hazırlanmıştır.

---

**Versiyon:** 0.0.1-SNAPSHOT  
**Son Güncelleme:** 2026-05-21
