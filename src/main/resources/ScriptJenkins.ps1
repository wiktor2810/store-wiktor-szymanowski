gc config.properties | Foreach-Object {
    $_ -replace "browser", "$env:browser1"
    } | Set-Content tempConfig
    rm config.properties
    rename-item tempConfig config.properties