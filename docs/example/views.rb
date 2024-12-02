tags = defined?(tags) ? tags : ""


def tags_filter(tags=[])
  puts "Tags: #{tags}"
end


tags_filter(tags=tags.split(","))
