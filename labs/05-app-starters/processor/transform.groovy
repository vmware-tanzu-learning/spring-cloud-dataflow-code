import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def jsonSlurper = new JsonSlurper()
def json = jsonSlurper.parseText(new String(payload))

if (json.zipcode == '94103') {
    json.quote = json.quote.toUpperCase()
    println "Awesome ZIPCODE, accepted and transformed, data to be sent..."
} else {
    println "Regular ZIPCODE, data to be sent without transformation..."
}

return JsonOutput.toJson(json)

