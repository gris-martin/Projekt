mess = "123456789012345678"

if len(mess) < 8:
    print mess
elif len(mess) < 17:
    print mess[:8] + "\n" + mess[8:]
else:
    print mess[:8] + "\n" + mess[8:16]
