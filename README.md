# JACKSON - JSON Property order aphabetical sorting

I wanted to demonstrate that JSON properties can be automatically sorted by Jackson.
I configure ObjectNode to use TreeMap sorting (instead of LinkedHashMap default). Since the serializer iterates over the properties in sorted order, the output will be in sorted order as well.

Advantages:
* This works recursively, so the lower level properties will be sorted as well.
