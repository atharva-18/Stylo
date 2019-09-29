import requests
import io
import sys

args = sys.argv

CONTENT_PATH = args[1]
STYLE_PATH = args[2]

# Example posting a local image file:

import requests
r = requests.post(
    "https://api.deepai.org/api/fast-style-transfer",
    files={
        'content': open(CONTENT_PATH, 'rb'),
        'style': open(STYLE_PATH, 'rb'),
    },
    headers={'api-key': ''}
)

# with open('o.txt', 'w') as f:
#     f.write(r.json()['output_url'])
#     f.close()

print(r.json()['output_url'])