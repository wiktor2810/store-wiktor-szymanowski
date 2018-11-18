gc config.properties | Foreach-Object {
    $_ -replace "browser", "$env:browser1"
    } | Set-Content temporary
    rm config.properties
    rename-item temporary config.properties