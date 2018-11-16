gc \src\main\resources\config.properties | Foreach-Object {
    $_ -replace "browser", "$env:browser1"
    } | Set-Content temporary
    rm config.properties
    rename-item temporary \src\main\resources\config.properties