<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Application - Vivek Tiwari</title>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to bottom, #2b5876, #4e4376); /* Gradient Background */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full Viewport Height */
        }
        .weather-card {
            width: 400px;
            border-radius: 15px;
            padding: 20px;
            background-color: #f9fafb;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .searchInput {
            display: flex;
            margin-bottom: 20px;
        }
        .searchInput input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px 0 0 5px;
            font-size: 14px;
        }
        .searchInput button {
            padding: 10px;
            background-color: #007BFF;
            border: none;
            border-radius: 0 5px 5px 0;
            color: white;
            cursor: pointer;
        }
        .searchInput button:hover {
            background-color: #0056b3;
        }
        .weather-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .weather-header h1 {
            font-size: 20px;
            margin: 0;
            color: #333;
        }
        .weather-main {
            margin-top: 20px;
        }
        .weather-main .icon {
            font-size: 80px;
            color: #007BFF;
        }
        .weather-main .temp {
            font-size: 48px;
            color: #333;
            font-weight: bold;
        }
        .weather-main .condition {
            font-size: 18px;
            color: #777;
            margin-top: 5px;
        }
        .weather-details {
            margin-top: 20px;
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 10px;
            font-size: 14px;
        }
        .weather-details div {
            text-align: center;
        }
        .weather-details i {
            font-size: 18px;
            color: #007BFF;
            display: block;
        }
    </style>
</head>
<body>
    <div class="weather-card">
        <!-- Search Form -->
        <form action="MyServlet" method="post" class="searchInput">
            <input type="text" placeholder="Search for city" id="searchInput" name="city"/>
            <button id="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>
        </form>

        <!-- Weather Information -->
        <div class="weather-header">
            <h1>${city}</h1>
            <span>${currentTime}</span>
        </div>
        <div class="weather-main">
            <!-- Dynamic Main Weather Icon -->
            <div class="icon">
                <i 
                    class="${
                        weatherCondition == 'Clear' ? 'fas fa-sun' : 
                        weatherCondition == 'Rain' ? 'fas fa-cloud-rain' : 
                        weatherCondition == 'Snow' ? 'fas fa-snowflake' : 
                        weatherCondition == 'Clouds' ? 'fas fa-cloud' : 
                        'fas fa-smog'
                    }"></i>
            </div>
            <div class="temp">${temperature}°C</div>
            <div class="condition">${weatherCondition}</div>
        </div>
        <div class="weather-details">
            <div>
                <i class="fas fa-tint"></i>
                <span>Humidity</span>
                <strong>${humidity}%</strong>
            </div>
            <div>
                <i class="fas fa-wind"></i>
                <span>Wind Speed</span>
                <strong>${windSpeed} km/h</strong>
            </div>
            <div>
                <i class="fas fa-eye"></i>
                <span>Visibility</span>
                <strong>${visibility} km</strong>
            </div>
            <div>
                <i class="fas fa-thermometer-half"></i>
                <span>Pressure</span>
                <strong>${pressure} mb</strong>
            </div>
        </div>
    </div>
</body>
</html>