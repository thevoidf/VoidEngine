#version 130

in vec3 out_color;
in vec2 out_uvs;

out vec4 color;

uniform sampler2D sampler;

void main()
{
    color = vec4(out_color, 1);
    color = texture(sampler, out_uvs);
}