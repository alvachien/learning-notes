import requests

searchterm = input("Please enter a search term:")

payload = {'term': searchterm, 'entity': 'album'}
resp = requests.get('https://itunes.apple.com/search', params=payload)
# print(resp.url)

respbody = resp.json()

# print(respbody)
print(f"The search returned {respbody['resultCount']} results")
for result in respbody["results"]:
    print(f"Artist: {result['artistName']} - Album: {result['collectionName']} - Track Count: {result['trackCount']}")
